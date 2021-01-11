package com.dev.market.domain.service;

import com.dev.market.domain.Product;
import com.dev.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository objProductRepository;

    public List<Product> getAll() {
        return objProductRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return objProductRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return objProductRepository.getByCategory(categoryId);
    }

    public Optional<List<Product>> getScarseProducts(int quantity){
        return objProductRepository.getScarseProducts(quantity);
    }

    public Product save(Product product){
        return objProductRepository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            objProductRepository.delete(productId);
            return true;
        }).orElse(false);
//        if (getProduct(productId).isPresent()){
//            objProductRepository.delete(productId);
//            return true;
//        }else{
//            return false;
//        }
    }
}
