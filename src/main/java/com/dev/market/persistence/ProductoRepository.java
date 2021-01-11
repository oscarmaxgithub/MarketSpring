package com.dev.market.persistence;

import com.dev.market.domain.Product;
import com.dev.market.domain.repository.ProductRepository;
import com.dev.market.persistence.crud.ProductoCrudRepository;
import com.dev.market.persistence.entity.Producto;
import com.dev.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository objProductoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos=(List<Producto>) objProductoCrudRepository.findAll();
        return mapper.toProducts(productos);
//        return (List<Producto>) objProductoCrudRepository.findAll();
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos=objProductoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);;
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos=objProductoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods->mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {

        return objProductoCrudRepository.findById(productId).map(producto ->mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto prod= mapper.toProducto(product);
        return mapper.toProduct(objProductoCrudRepository.save(prod));
    }

    @Override
    public void delete(int productId) {
        objProductoCrudRepository.deleteById(productId);
    }

//    public List<Producto> getByCategoria(int idCategoria) {
//        return objProductoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
//    }

//    public Optional<List<Producto>> getEscasos(int cantidad) {
//        return objProductoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
//    }

//    public Optional<Producto> getProducto(int idProducto) {
//        return objProductoCrudRepository.findById(idProducto);
//    }

//    public Producto saveProducto(Producto prod) {
//        return objProductoCrudRepository.save(prod);
//    }

//    public void deleteProducto(int idProducto) {
//        objProductoCrudRepository.deleteById(idProducto);
//    }

}
