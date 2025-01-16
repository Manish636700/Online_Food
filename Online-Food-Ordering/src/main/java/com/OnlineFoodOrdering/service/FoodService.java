package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.model.Category;
import com.OnlineFoodOrdering.model.Food;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.request.CreateFoodRequest;
import com.OnlineFoodOrdering.request.CreateRestaurantRequest;

import java.util.List;


public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food>getRestaurantFood(Long restaurantId,
                                       boolean isVegetarian,
                                       boolean isNonveg,
                                       boolean isSeasonal,
                                       String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
