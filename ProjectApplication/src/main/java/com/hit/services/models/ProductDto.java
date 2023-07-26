package com.hit.services.models;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String imageUrl;
    private Integer price;

    public ProductDto(String name, Integer price, String image) {
        this.name = name;
        this.imageUrl = image;
        this.price = price;
    }
}