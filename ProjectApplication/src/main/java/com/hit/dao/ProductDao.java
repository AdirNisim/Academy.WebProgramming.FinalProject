package com.hit.dao;

import com.hit.entities.Product;

/*
 * Product data access object.
 */
public class ProductDao extends BaseDao<Product, Integer> {

	/*
	 * Initializes product data access object instaince using a default path.
	 */
	public ProductDao() {
		this("ProjectApplication\\src\\main\\resources\\products.txt");
	}

	/*
	 * Initializes product data access object instaince using a custom path.
	 */
	public ProductDao(String path) {
		super(path);
	}
}
