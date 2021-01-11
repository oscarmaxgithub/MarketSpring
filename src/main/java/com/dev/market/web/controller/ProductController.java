package com.dev.market.web.controller;

import com.dev.market.domain.Product;
import com.dev.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService objProductService;

    @GetMapping("/all")
    @ApiOperation("Todos los productos del market")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 404,message = "No hay Productos"),
    } )
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(objProductService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("El producto segun su ID")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Se encontro"),
            @ApiResponse(code = 404,message = "No encontrado"),
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "El ID del producto",required = true,example = "13") @PathVariable("id") int productId) {
        return objProductService.getProduct(productId)
                .map(prod->new ResponseEntity<>(prod,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId) {
        return objProductService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/{quantity}")
    public ResponseEntity<List<Product>> getScarseProducts(@PathVariable("quantity") int quantity) {
        return objProductService.getScarseProducts(quantity)
                .map(prods->new ResponseEntity<>(prods,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(objProductService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
        if (objProductService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
//        return new objProductService.delete(productId);
    }

}
