package com.hit.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.model.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Represents a client-side request to search for products on a server using sockets.
 * Implements the Callable interface to allow execution and retrieval of results.
 */

public class SearchProductRequest implements Callable<QueryProductResponse> {

    /**
     * The executor service for launching the request.
     */
    private ExecutorService requestLauncher;

    /**
     * The socket connection to the server.
     */
    private Socket socketClient;

    /**
     * The port number for the server connection.
     */
    private int port;

    /**
     * The pattern used for product searching.
     */
    private String pattern;

    /**
     * Constructs a SearchProductRequest object with the specified port and search pattern.
     *
     * @param portInput the port number for the server connection
     * @param pattern   the pattern used for product searching
     */

    public SearchProductRequest(int portInput,String pattern) {
        this.port = portInput;
        this.pattern = pattern;
        this.requestLauncher = Executors.newFixedThreadPool(5);

    }

    /**
     * Constructs a SearchProductRequest object with the default port and the specified search pattern.
     * Uses the default port number 12345 and connects to the local server.
     *
     * @param pattern the pattern used for product searching
     */

    public SearchProductRequest(String pattern) {
        this(12345,pattern);

        try {
            this.socketClient = new Socket("localhost", this.port);
        }
        catch (IOException ex) {
            System.out.println("Socket server couldn't be initialized: " + ex.getMessage());
        }

    }

    /**
     * Executes the request to search for products on the server.
     * Establishes a connection with the server, sends the search pattern as a JSON payload,
     * and retrieves the response containing the queried products.
     *
     * @return the response containing the queried products
     * @throws Exception if an error occurs during the execution
     */

    @Override
    public QueryProductResponse call() throws Exception {
        try {
            socketClient = new Socket("localhost", this.port);
            ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream());
            String messageFromServer = (String) input.readObject();
            System.out.println(messageFromServer);

            Gson gson = new Gson();
            QueryProductRequest queryRequest = new QueryProductRequest();
            queryRequest.setTitle(this.pattern);
            Payload payload = new Payload("get", queryRequest);
            String json = gson.toJson(payload);
            output.writeObject(json);
            output.flush();

            String response = (String) input.readObject();
            Type type = new TypeToken<QueryProductResponse>() {
            }.getType();
            QueryProductResponse productRespond = gson.fromJson(response, type);
            output.close();
            input.close();
            System.out.println("Done");
            return productRespond;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Client session couldn't be initiated.");
            System.out.println("Client session couldn't be initiated: " + ex.getMessage());
            ex.printStackTrace();
        }
        return new QueryProductResponse(new ArrayList<Product>());
    }
}