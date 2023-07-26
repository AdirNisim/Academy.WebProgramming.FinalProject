package com.hit.model;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a response to a product query, containing a list of products.
 */
public class QueryProductResponse implements Serializable {

    /**
     * The list of products returned in the response.
     */
    private List<Product> products;

    /**
     * Constructs a QueryProductResponse object with the specified list of products.
     *
     * @param products the list of products in the response
     */
    public QueryProductResponse(List<Product> products) {
        this.products = products;
    }

    /**
     * Retrieves the list of products in the response.
     *
     * @return the list of products
     */
    public List<Product> getProducts() {
        return products;
    }

}
