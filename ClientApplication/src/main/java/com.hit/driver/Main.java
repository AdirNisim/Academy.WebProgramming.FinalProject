package com.hit.driver;
	
import java.util.*;

import com.hit.controllers.MainPageController;
import com.hit.model.Product;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;



public class Main extends Application {
    private List<Product> products;

    /**
     * The main entry point for the JavaFX application.
     * Initializes the JavaFX stage, loads the main page layout, and sets up the user interface.
     * @param primaryStage the primary stage of the JavaFX application
     */

	@Override
	public void start(Stage primaryStage) {
		products = new ArrayList<Product>();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainPage.fxml"));
            Parent root = loader.load();
            MainPageController controller = loader.getController();
            controller.SearchProducts("");

            Scene scene = new Scene(root, 1050, 730);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**
     * The main method that launches the JavaFX application.
     * @param args command line arguments
     */

	public static void main(String[] args) {
		launch(args);
	}
}
