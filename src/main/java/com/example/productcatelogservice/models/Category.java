package com.example.productcatelogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category(){
        this.setState(State.ACTIVE);
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
    }
}
