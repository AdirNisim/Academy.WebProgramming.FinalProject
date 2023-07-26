package com.hit.controllers;

import com.hit.model.Product;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Controller class for the product item view.
 */

public class ProductItemController {

    @FXML
    private ImageView product_image;

    @FXML
    private Text product_price;

    @FXML
    private Text product_title;

    /**
     * Sets the product details in the product item view.
     *
     * @param product the product object containing the details
     */
    public void setProduct(Product product) {
        product_title.setText(product.getName());
        product_price.setText(Integer.toString(product.getPrice()));
        product_image.setImage(new Image(product.getImage()));
    }
    
}
