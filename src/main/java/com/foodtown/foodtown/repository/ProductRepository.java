package com.foodtown.foodtown.repository;

import com.foodtown.foodtown.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

}