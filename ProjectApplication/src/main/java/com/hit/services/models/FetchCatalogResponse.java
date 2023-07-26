package com.hit.services.models;

import java.util.List;

public class FetchCatalogResponse {
    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}