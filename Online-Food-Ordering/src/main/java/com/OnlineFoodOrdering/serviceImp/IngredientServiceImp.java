package com.OnlineFoodOrdering.serviceImp;

import com.OnlineFoodOrdering.model.IngredientCategory;
import com.OnlineFoodOrdering.model.IngredientsItems;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.repository.IngredientCategoryRepository;
import com.OnlineFoodOrdering.repository.IngredientItemRepository;
import com.OnlineFoodOrdering.repository.RestaurantRepository;
import com.OnlineFoodOrdering.service.IngredientsService;
import com.OnlineFoodOrdering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp  implements IngredientsService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

        if(ingredientCategory.isEmpty()) {
            throw new Exception("Ingredient Category not found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {

        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<IngredientsItems> findRestaurantsIngredientsItems(Long restaurantId) {

        return ingredientItemRepository.findByRestaurantId(restaurantId);

    }
    @Override
    public IngredientsItems createIngredientsItems(Long restaurantId, String ingredientName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);

        IngredientsItems ingredientsItems = new IngredientsItems();

        ingredientsItems.setName(ingredientName);
        ingredientsItems.setRestaurant(restaurant);
        ingredientsItems.setCategory(ingredientCategory);

       IngredientsItems items = ingredientItemRepository.save(ingredientsItems);
        ingredientCategory.getIngredientsItems().add(items);
//        ingredientCategoryRepository.save(ingredientCategory);
       return items;
    }



    @Override
    public IngredientsItems updateStock(Long id) throws Exception {

        Optional<IngredientsItems> optionalIngredientsItems = ingredientItemRepository.findById(id);

        if(optionalIngredientsItems.isEmpty()) {
            throw new Exception("Ingredient Item not found");
        }
        IngredientsItems ingredientsItem = optionalIngredientsItems.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
