package com.javatechie.spring.aop.api.repository;

import com.javatechie.spring.aop.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
