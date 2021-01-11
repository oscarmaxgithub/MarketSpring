package com.dev.market.web.controller;

import com.dev.market.domain.Product;
import com.dev.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService objProductService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return objProductService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return objProductService.getProduct(productId);
    }

    @GetMapping("/category/{id}")
    public Optional<List<Product>> getByCategory(@PathVariable("id") int categoryId) {
        return objProductService.getByCategory(categoryId);
    }

    @GetMapping("/product/{quantity}")
    public Optional<List<Product>> getScarseProducts(@PathVariable("quantity") int quantity) {
        return objProductService.getScarseProducts(quantity);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return objProductService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return objProductService.delete(productId);
    }

}
