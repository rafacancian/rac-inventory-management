package com.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
