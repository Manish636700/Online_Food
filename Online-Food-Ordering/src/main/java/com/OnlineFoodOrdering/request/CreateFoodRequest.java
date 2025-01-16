package com.OnlineFoodOrdering.request;


import com.OnlineFoodOrdering.model.Category;
import com.OnlineFoodOrdering.model.IngredientsItems;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;

    private List<IngredientsItems>ingredients;


}
