package com.dev.market.persistence.mapper;

import com.dev.market.domain.Category;
import com.dev.market.domain.Product;
import com.dev.market.persistence.entity.Categoria;
import com.dev.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    /**
     * InheritInverseConfiguration hace que el mapeo anterior se inverso  al devolver un mapeo
     * jalar parametro de PRODUCTO a PRODUCT
     * cuando haya una categororia como objeto, agregar el USES para que spring sepa que debe mapear con esa clase
     * @param product
     * @return
     */
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras",ignore = true)//ignora el listado de productos propio de la clase Producto
    Producto toProducto(Product product);
}
