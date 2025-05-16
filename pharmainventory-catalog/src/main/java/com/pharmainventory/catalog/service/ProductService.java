package com.pharmainventory.catalog.service;

import com.pharmainventory.catalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
    Product findById(Long id);
    Page<Product> listAll(Pageable pageable);
    List<Product> findByPriceRange(BigDecimal min, BigDecimal max);
    Page<Product> searchByName(String term, Pageable pageable);
}
