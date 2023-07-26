package com.hit.services;

import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hit.algorithm.ILooseQueryAlgorithm;
import com.hit.dao.IDao;
import com.hit.entities.Product;
import com.hit.services.models.AddProductRequest;
import com.hit.services.models.AddProductResponse;
import com.hit.services.models.ProductDto;
import com.hit.services.models.QueryProductRequest;
import com.hit.services.models.QueryProductResponse;

/*
 * Catalog service handles business logic and database operations.
 */
public class CatalogService implements ICatalogService {
	ExecutorService requestLauncher;
	IDao<Product, Integer> productDao;
	ILooseQueryAlgorithm algorithmService;

	public CatalogService(IDao<Product, Integer> productDao, ILooseQueryAlgorithm algorithmService) {
		this.requestLauncher = Executors.newFixedThreadPool(5);
		this.productDao = productDao;
		this.algorithmService = algorithmService;
	}

	@Override
	public Future<AddProductResponse> addProduct(AddProductRequest request) {
		Product product = new Product(request.getTitle(), request.getImage(), request.getPrice());

		return this.requestLauncher.submit(() -> {
			this.productDao.save(product);

			return new AddProductResponse(true);
		});
	}

	@Override
	public Future<QueryProductResponse> queryProduct(QueryProductRequest request) {
		String queryString = request.getTitle();

		return this.requestLauncher.submit(() -> {
			HashMap<Integer, Product> productEntries = this.productDao.getAll();
			QueryProductResponse response = new QueryProductResponse();

			if (request.getTitle() == "") {
				response.setProducts(
						productEntries
								.values()
								.stream()
								.map(x -> new ProductDto(x.getTitle(), x.getPrice(), x.getImage()))
								.collect(Collectors.toList()));
				return response;
			}

			this.algorithmService.setCatalog(productEntries.values()
					.stream()
					.map(x -> x.getTitle())
					.collect(Collectors.toList()));

			HashSet<String> queryResult = new HashSet<>(this.algorithmService.queryCatalog(queryString));

			response.setProducts(
					productEntries
							.values()
							.stream()
							.filter(x -> queryResult.contains(x.getTitle()))
							.map(x -> new ProductDto(x.getTitle(), x.getPrice(), x.getImage()))
							.collect(Collectors.toList()));

			return response;
		});
	}
}
