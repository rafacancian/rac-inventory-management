package com.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
