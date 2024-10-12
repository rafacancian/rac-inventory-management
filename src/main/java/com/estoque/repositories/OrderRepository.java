package com.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
