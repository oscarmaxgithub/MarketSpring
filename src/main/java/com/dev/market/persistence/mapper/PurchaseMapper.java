package com.dev.market.persistence.mapper;

import com.dev.market.domain.Purchase;
import com.dev.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra" ,target = "purchaseId"),
            @Mapping(source = "idCliente" ,target = "clienteId"),
            @Mapping(source = "fecha" ,target = "date"),
            @Mapping(source = "medioPago" ,target = "paymentMethod"),
            @Mapping(source = "productos" ,target = "items"),
            @Mapping(source = "estado" ,target = "state"),
            @Mapping(source = "comentario" ,target = "commet"),
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente",ignore = true)
    Compra toCompra(Purchase purchase);
}
