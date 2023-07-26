package com.hit.model;

/**
 * Represents a product with a name, price, and image URL.
 */

public class Product {
    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private int price;

    /**
     * The URL of the product image.
     */
    private String imageUrl;

    /**
     * Constructs a Product object with the specified name, price, and image URL.
     *
     * @param name     the name of the product
     * @param price    the price of the product
     * @param image URL the URL of the product image
     */

    public Product(String name, int price, String image) {
        this.name = name;
        this.price = price;
        this.imageUrl = image;
    }


    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */

    public void setName(String title) {
        this.name = title;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */

    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */

    public void SetPrice(int name) {
        this.price = price;
    }

    /**
     * Retrieves the URL of the product image.
     *
     * @return the URL of the product image
     */
    public String getImage() {
        return imageUrl;
    }

    /**
     * Sets the URL of the product image.
     *
     * @param imageUrl the URL of the product image
     */
    public void setImage(String image) {
        this.imageUrl = image;
    }
}
