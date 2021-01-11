package com.dev.market.domain.service;

import com.dev.market.domain.Purchase;
import com.dev.market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository objPurchaseRepository;

    public List<Purchase> getAll(){
        return  objPurchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getPurchaseByClient(String clientId){
        return  objPurchaseRepository.getPurchaseByClient(clientId);
    }

    public  Purchase save(Purchase objpurchase){
        return objPurchaseRepository.save(objpurchase);
    }

}
