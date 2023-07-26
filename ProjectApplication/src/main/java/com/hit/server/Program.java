package com.hit.server;

import com.hit.algorithm.implementation.OnlineSearchAlgorithm;
import com.hit.dao.ProductDao;
import com.hit.services.CatalogService;

/*
 * Main program class, executes the application.
 */
public class Program {

	/*
	 * Main function, starts the server outside of the main thread and initiates dependencies.
	 */
	public static void main(String[] args) {
		new Thread(new Server(new CatalogService(new ProductDao(), new OnlineSearchAlgorithm()))).start();
	}
}
