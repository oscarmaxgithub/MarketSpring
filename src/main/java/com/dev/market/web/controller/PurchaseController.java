package com.dev.market.web.controller;

import com.dev.market.domain.Product;
import com.dev.market.domain.Purchase;
import com.dev.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService objPurhaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(objPurhaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Purchase>> getPurchaseByClient(@PathVariable("id") String clientId) {
        return objPurhaseService.getPurchaseByClient(clientId)
                .map(purchase->new ResponseEntity<>(purchase,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(objPurhaseService.save(purchase),HttpStatus.CREATED);
    }

}
