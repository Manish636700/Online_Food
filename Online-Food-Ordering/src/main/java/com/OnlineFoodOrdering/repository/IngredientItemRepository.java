package com.OnlineFoodOrdering.repository;

import com.OnlineFoodOrdering.model.IngredientsItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItems,Long> {

    List<IngredientsItems> findByRestaurantId(Long id);
}
