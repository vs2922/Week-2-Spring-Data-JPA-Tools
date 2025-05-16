package com.pharmainventory.catalog.service;

import com.pharmainventory.catalog.model.Category;
import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> listAll();
}
