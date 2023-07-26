package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hit.services.ICatalogService;

/*
 * Server initiates socket listening, handles requests and common errors.
 */
public class Server implements Runnable {
	private static boolean serverUp = true;

	private ServerSocket socketServer;
	private int port;
	private ICatalogService catalogService;

	/*
	 * Initiates the server with dependent catalog service and default port: 12345.
	 */
	public Server(ICatalogService catalogService) {
		this(12345);

		this.catalogService = catalogService;

		try {
			this.socketServer = new ServerSocket(this.port);
		} catch (IOException ex) {
			System.out.println("Socket server couldn't be initialized: " + ex.getMessage());
		}
	}

	/*
	 * Initializes the server with a custom port.
	 */
	public Server(int portInput) {
		this.port = portInput;
	}

	/*
	 * Server execution functionality.
	 */
	@Override
	public void run() {
		try {
			while (serverUp) {
				Socket client = this.socketServer.accept();

				new Thread(new Request(client, this.catalogService)).start();
			}
			this.socketServer.close();
		} catch (IOException ex) {
			System.out.println("Client session couldn't be initiated.");
		}
	}
}
