package com.dev.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    /**
     * Clase creada con el proposito de usar patron DATA MAPPER
     */
    private int categoryId;
    private String category;
    private boolean active;

}
