package com.hit.client;

import java.io.IOException;
import java.net.Socket;
import com.google.gson.Gson;
import com.hit.model.Payload;
import com.hit.model.ProductDto;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents a client-side request to add a product to a server using sockets.
 * Implements the Runnable interface to allow concurrent execution in a separate thread.
 */

public class AddProductRequest implements Runnable {

	/**
	 * The socket connection to the server.
	 */
	private Socket socketClient;

	/**
	 * The port number for the server connection.
	 */
	private int port;

	/**
	 * The product to be added.
	 */
	private ProductDto newProduct;


	/**
	 * Constructs an AddProductRequest object with the specified port and product.
	 *
	 * @param portInput  the port number for the server connection
	 * @param addProduct the product to be added
	 */

	public AddProductRequest(int portInput, ProductDto addProduct) {
		this.port = portInput;
		this.newProduct = addProduct;
	}


	/**
	 * Constructs an AddProductRequest object with the default port and the specified product.
	 * Uses the default port number 12345 and connects to the local server.
	 *
	 * @param addProduct the product to be added
	 */

	public AddProductRequest(ProductDto addProduct) {
		this(12345, addProduct);
		
		try {
			this.socketClient = new Socket("localhost", this.port);
		}
		catch (IOException ex) {
			System.out.println("Socket server couldn't be initialized: " + ex.getMessage());
		}
		
	}

	/**
	 * Executes the request to add the product to the server.
	 * Establishes a connection with the server, sends the product information as a JSON,
	 * and closes the connection.
	 */

	@Override
	public void run() {
		try {
				socketClient = new Socket("localhost", this.port);
				ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
				ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream());
				String messageFromServer=(String)input.readObject();
				System.out.println(messageFromServer);

				Gson gson = new Gson();
				Payload payload = new Payload("add",this.newProduct);
				String json = gson.toJson(payload);
				output.writeObject(json);

				output.flush();		
				output.close();
				input.close();
				System.out.println("Done");
		}
		
		catch (IOException | ClassNotFoundException ex) {
		    System.out.println("Client session couldn't be initiated.");
		    System.out.println("Client session couldn't be initiated: " + ex.getMessage());
		    ex.printStackTrace();
		}
		
	}
}
