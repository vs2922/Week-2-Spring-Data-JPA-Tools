package com.pharmainventory.catalog.api;

import com.pharmainventory.catalog.model.Category;
import com.pharmainventory.catalog.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService svc;

    public CategoryController(CategoryService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<Category> list() {
        return svc.listAll();
    }

    @PostMapping
    public Category create(@Valid @RequestBody Category c) {
        return svc.create(c);
    }
}
