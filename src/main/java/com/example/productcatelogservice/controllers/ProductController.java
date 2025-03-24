package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.CategoryDto;
import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")

public class ProductController {

    private IProductService productService;

    public ProductController(@Qualifier("SPS") IProductService productService){

        this.productService = productService;
    }
    //Get Single Product
    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id){
        if(id<=0){
            throw new IllegalArgumentException("Invalid Product Id");
        }
        Product product= productService.getProductById(id);
        if(product==null){
            return null;
        }
        return from(product);

    }

    //Get all Products
    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<ProductDto>();
        for (Product product : products) {
            productDtos.add(from(product));
        }
        return productDtos;
//        return products.stream()
//                .map(this::from)
//                .collect(Collectors.toList());

    }

    //Delete Product
    @DeleteMapping ("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }


    //update Product
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody Product product){

        Product response= productService.updateProduct(id, product);
        return from(response);
    }

    //Create Product
    @PostMapping
    public ProductDto createProduct(@RequestBody Product product){

        Product response = productService.createProduct(product);
        return from(response);
    }


    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    private Product to(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        category.setId(productDto.getCategory().getId());
        category.setDescription(productDto.getCategory().getDescription());
        product.setCategory(category);
        return product;
    }




}
