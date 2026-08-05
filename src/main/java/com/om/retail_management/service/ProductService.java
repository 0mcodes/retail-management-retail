package com.om.retail_management.service;

import com.om.retail_management.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);
}