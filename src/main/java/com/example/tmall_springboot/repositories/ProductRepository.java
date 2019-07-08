package com.example.tmall_springboot.repositories;

import com.example.tmall_springboot.domains.Category;
import com.example.tmall_springboot.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(Category category, Pageable pageable);
}