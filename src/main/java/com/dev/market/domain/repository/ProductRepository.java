package com.dev.market.domain.repository;

import com.dev.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    /**
     * Repositorio creado para cumplir el patron DATA MAPPER
     * y no revelar los atributos de la base de datos
     * @return
     */
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
