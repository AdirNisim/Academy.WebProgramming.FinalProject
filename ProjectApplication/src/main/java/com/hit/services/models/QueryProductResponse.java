package com.hit.services.models;

import java.io.Serializable;
import java.util.List;

public class QueryProductResponse implements Serializable {
    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
