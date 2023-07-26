package com.hit.controllers;

import com.hit.client.AddProductRequest;
import com.hit.model.ProductDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the "AddProduct" view.
 */
public class AddProductController {

    @FXML
    private Button insertProductButton;

    @FXML
    private TextField ImageInput;

    @FXML
    private TextField PriceInput;

    @FXML
    private TextField nameInput;


    /**
     * Event handler for the "InsertProduct" button.
     * Reads the input values from the text fields,
     * creates a new ProductDto object, and starts a new thread
     * to send an "AddProductRequest" with the product data.
     * Closes the current window upon completion.
     *
     * @param event the action event
     */
    @FXML
    void InsertProduct(ActionEvent event) {
        String image = ImageInput.getText();
        String name = nameInput.getText();
        String price = PriceInput.getText();
        ProductDto addProduct = new ProductDto(name,image,Integer.parseInt(price));
        Thread thread = new Thread(new AddProductRequest(addProduct));
        thread.start();
        // Close the current window
        ((Stage)this.insertProductButton.getScene().getWindow()).close();
    }

}