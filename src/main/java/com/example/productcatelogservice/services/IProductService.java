package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id,Product product);
    Product createProduct(Product product);
    void deleteProduct(Long id);
}
