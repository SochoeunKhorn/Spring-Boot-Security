package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.Category;

import java.util.List;

public interface CategoryService {
    // crud
    Category createCategory(Category request);
    List<Category> getAllCategory();
    Category getCategory(Integer categoryId);
    Category updateCategory(Integer categoryId,Category category);
    void deleteCategory(Integer categoryId);
}
