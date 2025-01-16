package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.model.IngredientCategory;
import com.OnlineFoodOrdering.model.IngredientsItems;
import com.sun.jdi.LongValue;

import java.util.List;

public interface IngredientsService {


    public IngredientCategory createIngredientCategory(String name , Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory>findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;

    public IngredientsItems createIngredientsItems(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItems> findRestaurantsIngredientsItems(Long restaurantId);

    public IngredientsItems updateStock(Long id) throws Exception;




}
