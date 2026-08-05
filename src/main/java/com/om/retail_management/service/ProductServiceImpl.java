package com.om.retail_management.service;

import com.om.retail_management.entity.Product;
import com.om.retail_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product addProduct(Product product) {

        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {

        repository.deleteById(id);
    }
}
