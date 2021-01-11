package com.dev.market.domain.repository;

import com.dev.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getPurchaseByClient(String clientId);
    Purchase save(Purchase objpurchase);
}
