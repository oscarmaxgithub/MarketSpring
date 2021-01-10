package com.dev.market.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    /**
     * Clase creada con el proposito de usar patron DATA MAPPER
     */
    private int productId;
    private String name;
    private String categoryId;
    private BigDecimal price;
    private int stock;
    private boolean active;
    private Category category;
}
