package com.pharmainventory.catalog.service;

import com.pharmainventory.catalog.exception.ResourceNotFoundException;
import com.pharmainventory.catalog.model.*;
import com.pharmainventory.catalog.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository prodRepo;
    private final CategoryRepository catRepo;
    private final TagRepository tagRepo;

    public ProductServiceImpl(ProductRepository p, CategoryRepository c, TagRepository t) {
        this.prodRepo = p;
        this.catRepo = c;
        this.tagRepo = t;
    }

    @Override
    public Product create(Product p) {
        Category cat = catRepo.findById(p.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", p.getCategory().getId()));
        p.setCategory(cat);

        p.setTags(p.getTags().stream()
                .map(tag -> tagRepo.findByName(tag.getName())
                        .orElseGet(() -> tagRepo.save(tag)))
                .collect(Collectors.toSet()));

        return prodRepo.save(p);
    }

    @Override
    public Product update(Long id, Product p) {
        Product existing = findById(id);
        existing.setName(p.getName());
        existing.setPrice(p.getPrice());

        Category cat = catRepo.findById(p.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", p.getCategory().getId()));
        existing.setCategory(cat);

        existing.setTags(p.getTags().stream()
                .map(tag -> tagRepo.findByName(tag.getName())
                        .orElseGet(() -> tagRepo.save(tag)))
                .collect(Collectors.toSet()));

        return prodRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        prodRepo.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return prodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> listAll(Pageable pageable) {
        return prodRepo.findAll(pageable);
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal min, BigDecimal max) {
        return prodRepo.findByPriceBetween(min, max);
    }

    @Override
    public Page<Product> searchByName(String term, Pageable pageable) {
        return prodRepo.findByNameContainingIgnoreCase(term, pageable);
    }
}
