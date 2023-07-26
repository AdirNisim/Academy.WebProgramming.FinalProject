package com.hit.tests;

import java.util.*;
import java.util.Map.Entry;
import com.hit.dao.ProductDao;
import com.hit.entities.Product;

public class MyServiceTests {

    public void canGetAllProducts() {

        ProductDao productDao = new ProductDao();

        Set<Entry<Integer, Product>> products = productDao.getAll().entrySet();

    }
}
