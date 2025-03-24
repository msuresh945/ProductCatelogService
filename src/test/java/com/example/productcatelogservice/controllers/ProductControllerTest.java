package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockitoBean
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    void TestGetProductDetailsById_WithValidProductId_RunSuccessfully() {
        long productId = 2L;
        when(productService.getProductById(productId)).thenReturn(new Product());

        ProductDto productDto = productController.getProductDetails(productId);

        assertNotNull(productDto);
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void createProduct() {
    }
}