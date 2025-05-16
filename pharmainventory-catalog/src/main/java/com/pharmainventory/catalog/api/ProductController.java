package com.pharmainventory.catalog.api;

import com.pharmainventory.catalog.model.Product;
import com.pharmainventory.catalog.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Page<Product> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return svc.listAll(PageRequest.of(page, size, Sort.by("name")));
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return svc.findById(id);
    }

    @GetMapping("/search")
    public Page<Product> search(@RequestParam String q,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        return svc.searchByName(q, PageRequest.of(page, size));
    }

    @GetMapping("/price")
    public List<Product> byPrice(@RequestParam BigDecimal min,
                                 @RequestParam BigDecimal max) {
        return svc.findByPriceRange(min, max);
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product p) {
        return svc.create(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product p) {
        return svc.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
