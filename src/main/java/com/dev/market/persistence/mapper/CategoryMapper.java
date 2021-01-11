package com.dev.market.persistence.mapper;

import com.dev.market.domain.Category;
import com.dev.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    /**
     * InheritInverseConfiguration hace que el mapeo anterior se inverso  al devolver un mapeo
     *
     * @return
     */
    @InheritInverseConfiguration
    @Mapping(target = "productos",ignore = true)//ignora el listado de productos propio de la clase Categoria
    Categoria toCategoria(Category category);
}
