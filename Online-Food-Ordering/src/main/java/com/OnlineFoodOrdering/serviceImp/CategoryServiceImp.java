package com.OnlineFoodOrdering.serviceImp;

import com.OnlineFoodOrdering.model.Category;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.repository.CategoryRepository;
import com.OnlineFoodOrdering.service.CategoryService;
import com.OnlineFoodOrdering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp  implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public Category CreateCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        List<Category> categories= categoryRepository.findByNameAndRestaurantId(name, restaurant.getId());
        if (!categories.isEmpty()) {
            throw new Exception("Category already exists.");
        }
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Category findCategoryById(Long categoryId) throws Exception {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty())
        {
            throw new Exception("Category not found");
        }
        return category.get();
    }

    @Override
    public void deleteCategoryByNameAndUserId(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        List<Category> categories= categoryRepository.findByNameAndRestaurantId(name, restaurant.getId());
        if (!categories.isEmpty()) {
            categoryRepository.delete(categories.get(0));
            return;

        }
        throw new Exception("Category Not Found");
    }
}
