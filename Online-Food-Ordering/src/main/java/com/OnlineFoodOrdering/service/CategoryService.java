package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.model.Category;

import java.util.List;

public interface CategoryService {

    public Category CreateCategory(String name,Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;

    public Category findCategoryById(Long categoryId) throws Exception;

    public void deleteCategoryByNameAndUserId(String name, Long userId) throws Exception;
}
