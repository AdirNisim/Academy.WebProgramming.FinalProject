package com.hit.model;

import java.io.Serializable;

/**
 * Represents a data transfer object (DTO) for a product.
 * Implements the Serializable interface to allow for serialization.
 */
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * The title of the product.
     */
    private String title;

    /**
     * The image of the product.
     */
    private String image;

    /**
     * The price of the product.
     */
    private Integer price;

    /**
     * Constructs a ProductDto object with the specified title, image, and price.
     *
     * @param title the title of the product
     * @param image the image of the product
     * @param price the price of the product
     */
    public ProductDto(String name, String image, int price) {
    	this.title = name;
    	this.image = image;
        this.price = price;
    }
}
