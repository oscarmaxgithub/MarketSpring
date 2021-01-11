package com.dev.market.persistence.mapper;

import com.dev.market.domain.PurchaseItem;
import com.dev.market.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    /**
     * como el SOURCE y el TARGET se llaman igual se puede obviar el agregar al mapeo de mapper
     * */

    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quantity"),
            @Mapping(source = "estado",target = "active"),
    })
    PurchaseItem toPurchaseItem(CompraProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra",ignore = true),
            @Mapping(target = "producto",ignore = true),
            @Mapping(target = "id.idCompra",ignore = true),
    })
    CompraProducto toCompraProducto(PurchaseItem item);
}
