package com.example.productcatelogservice.dtos.fakestore;

import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;

    public static Product to(FakeStoreProductResponseDto fakeStoreProductResponseDto) {
        Product product = new Product();
        product.setId(fakeStoreProductResponseDto.getId());
        product.setName(fakeStoreProductResponseDto.getTitle());
        product.setDescription(fakeStoreProductResponseDto.getDescription());
        product.setImageUrl(fakeStoreProductResponseDto.getImage());
        Category category1 = new Category();
        category1.setName(fakeStoreProductResponseDto.getCategory());
        product.setCategory(category1);
        product.setPrice(fakeStoreProductResponseDto.getPrice());
        product.setImageUrl(fakeStoreProductResponseDto.getImage());
        return product;
    }
}
