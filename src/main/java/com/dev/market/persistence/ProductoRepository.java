package com.dev.market.persistence;

import com.dev.market.persistence.crud.ProductoCrudRepository;
import com.dev.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private ProductoCrudRepository objProductoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) objProductoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return objProductoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return objProductoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true) ;
    }


}
