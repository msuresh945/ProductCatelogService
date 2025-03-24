package com.example.productcatelogservice.dtos.fakestore;

import com.example.productcatelogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto {
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;

    public static FakeStoreProductRequestDto from(Product product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle(product.getName());
        fakeStoreProductRequestDto.setDescription(product.getDescription());
        fakeStoreProductRequestDto.setCategory(product.getCategory().getName());
        fakeStoreProductRequestDto.setPrice(product.getPrice());
        fakeStoreProductRequestDto.setImage(product.getImageUrl());
        return fakeStoreProductRequestDto;
    }
}
