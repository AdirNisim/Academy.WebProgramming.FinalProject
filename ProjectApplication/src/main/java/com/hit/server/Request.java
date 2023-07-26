package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.server.models.Payload;
import com.hit.server.models.RouteOutlet;
import com.hit.services.ICatalogService;
import com.hit.services.models.AddProductRequest;
import com.hit.services.models.QueryProductRequest;
import com.hit.services.models.QueryProductResponse;

/*
 * Handles routing, serialization and socket consumption/dispach.
 */
public class Request implements Runnable {

    private Socket client;
    private ICatalogService catalogService;

    /*
     * Initializes api operation instance requested by a given client. 
     */
    public Request(Socket client, ICatalogService catalogService) {
        this.client = client;
        this.catalogService = catalogService;
    }

    /*
     * Request operation execution.
     */
    @Override
    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());

            this.write(output, "you are connected to the server");

            Gson gson = new Gson();
            String json = (String) input.readObject();
            RouteOutlet data = gson.fromJson(json, RouteOutlet.class);
            Type type;

            switch (data.getRoute()) {
                case "add":
                    type = new TypeToken<Payload<AddProductRequest>>() {
                    }.getType();

                    Payload<AddProductRequest> addProductRequest = gson.fromJson(json, type);
                    this.catalogService.addProduct(addProductRequest.getdata()).get();
                    break;
                case "get":
                    type = new TypeToken<Payload<QueryProductRequest>>() {
                    }.getType();

                    Payload<QueryProductRequest> queryProductRequest = gson.fromJson(json, type);

                    QueryProductResponse response = this.catalogService.queryProduct(queryProductRequest.getdata())
                            .get();

                    Gson gsonResponde = new Gson();
                    String jsonResponde = gsonResponde.toJson(response);
                    output.writeObject(jsonResponde);
                    output.flush();
                    break;
                default:
                    break;
            }

            System.out.println("message from the client: K");

            output.close();
            input.close();
        } catch (Exception ex) {
            System.out.println("Server error occured: " + ex.getMessage());
        } finally {
            System.out.println("Client session was shut down..");
        }
    }

    /*
     * Server message handler.
     */
    private void write(ObjectOutputStream output, String message) {
        try {
            output.writeObject(message);
            output.flush();
            System.out.println("A message has been written.");
        } catch (IOException ex) {
            System.out.println("Couldn't write data to server: " + ex.getMessage());
        }
    }
}
