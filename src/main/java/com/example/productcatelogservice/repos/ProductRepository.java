package com.example.productcatelogservice.repos;

import com.example.productcatelogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    @Override
    List<Product> findAll();


}
