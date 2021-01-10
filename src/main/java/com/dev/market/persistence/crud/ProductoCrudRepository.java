package com.dev.market.persistence.crud;


import com.dev.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
/**
 * CrudRepository
 * @Producto: primer paremtro que es la tabla que se hara el Crud
 * @Integer: Tipo de dato del PK de la tabla a hacer Crud
 */

//@Query(value = "SELECT * FROM productos WHERE id_categoria = ?",nativeQuery = true)
List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
/**
 * cuando se sigue la nomenclatura de QueryMethods
 * hay que seguir la norma de nombre como "findBy"+IdCategoria...
 * si es declara con @Query, no es necesario respetar la norma de Querymetohd y se puede llamar al metodo como se guste
 */


}
