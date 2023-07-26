package com.hit.controllers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hit.client.SearchProductRequest;
import com.hit.model.Product;

import com.hit.model.QueryProductResponse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller class for the main page view.
 */
public class MainPageController {

    @FXML
    private Button AddProductButton;

    @FXML
    private TextField searchInput;


    @FXML
    private ScrollPane scroll_panel; 
    
    @FXML
    private TilePane  productContainer;

    @FXML
    void OnInputChange(InputMethodEvent event) {
    }


    /**
     * Event handler for key typed in the search text field.
     * Calls the SearchProducts method to search for products based on the entered pattern.
     *
     * @param event the key event
     */
    @FXML
    void OnKeyTyped(KeyEvent event) {
        SearchProducts(searchInput.getText());
    }

    /**
     * Searches for products based on the specified pattern.
     *
     * @param pattern the pattern to search for
     */
    public void SearchProducts(String pattern) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        SearchProductRequest searchRequest = new SearchProductRequest(pattern);
        Future<QueryProductResponse>  futureResponse =  executor.submit(searchRequest);
         try {
             QueryProductResponse response = futureResponse.get();
             setProductModels(response.getProducts());
             executor.shutdown();
         }
         catch (InterruptedException | ExecutionException e) {
             executor.shutdown();
         }
    }

    /**
     * Event handler for the "AddProduct" button.
     * Opens the "AddProductForm" window and adds a listener to refresh the product list upon window close.
     *
     * @param event the action event
     */
    @FXML
    void btnAddProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddProductForm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root));
            stage.show();

            stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    SearchProducts(searchInput.getText());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the product models in the product container.
     * Clears the product container and creates a product item for each product model.
     *
     * @param productModels the list of product models
     */
    public void setProductModels(List<Product> productModels) {
        productContainer.getChildren().clear();
        if (productModels == null) {
            productContainer.getChildren().clear();
        }
        for (Product Product : productModels) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProductItem.fxml"));
                AnchorPane productItem = loader.load();
                ProductItemController itemController = loader.getController();
                itemController.setProduct(Product);
                productContainer.getChildren().add(productItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}