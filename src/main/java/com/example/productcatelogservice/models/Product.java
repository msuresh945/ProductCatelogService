package com.example.productcatelogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    @ManyToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Category category;
    private String imageUrl;
    private Boolean isPrime;

    public Product() {
        this.isPrime = false;
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
        this.setState(State.ACTIVE);

    }
}
