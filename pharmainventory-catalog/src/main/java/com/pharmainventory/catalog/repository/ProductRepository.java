package com.pharmainventory.catalog.repository;

import com.pharmainventory.catalog.model.Product;
import com.pharmainventory.catalog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    Page<Product> findByCategory_Name(String categoryName, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String term, Pageable pageable);
    long countByCategory(Category category);
}
