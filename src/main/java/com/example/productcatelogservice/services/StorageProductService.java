package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.repos.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("SPS")
public class StorageProductService implements IProductService {

    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productoptional = productRepository.findById(product.getId());

        return productoptional.orElseGet(() -> productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            productRepository.deleteById(id);
        }

    }
}
