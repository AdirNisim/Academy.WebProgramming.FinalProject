package com.hit.services;

import java.util.concurrent.Future;

import com.hit.services.models.AddProductRequest;
import com.hit.services.models.AddProductResponse;
import com.hit.services.models.QueryProductRequest;
import com.hit.services.models.QueryProductResponse;

/*
 * Catalog service handles business logic and database operations.
 */
public interface ICatalogService {
	/*
	 * Adds a product to the database for data persistence.
	 */
	public Future<AddProductResponse> addProduct(AddProductRequest request);

	/*
	 * Queries products from the catalog with a given search algorithm.
	 */
	public Future<QueryProductResponse> queryProduct(QueryProductRequest request);
}
