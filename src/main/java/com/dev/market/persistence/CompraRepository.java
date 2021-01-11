package com.dev.market.persistence;

import com.dev.market.domain.Purchase;
import com.dev.market.domain.repository.PurchaseRepository;
import com.dev.market.persistence.crud.CompraCrudRepository;
import com.dev.market.persistence.entity.Compra;
import com.dev.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository objCompraCrudrepository;

    @Autowired
    private PurchaseMapper objPurchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return objPurchaseMapper.toPurchases((List<Compra>) objCompraCrudrepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getPurchaseByClient(String clientId) {
        return objCompraCrudrepository.findByIdCliente(clientId)
                .map(compras-> objPurchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase objpurchase) {
        Compra compra=objPurchaseMapper.toCompra(objpurchase);
        compra.getProductos().forEach(producto->producto.setCompra(compra));
        return objPurchaseMapper.toPurchase(objCompraCrudrepository.save(compra));
    }
}
