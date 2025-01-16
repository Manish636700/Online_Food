package com.OnlineFoodOrdering.AdminController;

import com.OnlineFoodOrdering.model.IngredientCategory;
import com.OnlineFoodOrdering.model.IngredientsItems;
import com.OnlineFoodOrdering.request.IngredientCategoryRequest;
import com.OnlineFoodOrdering.request.IngredientRequest;
import com.OnlineFoodOrdering.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;


    @PostMapping("/category")
    public ResponseEntity<IngredientCategory>createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {

        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItems>createIngredientsItems(
            @RequestBody IngredientRequest req) throws Exception {
        IngredientsItems items = ingredientsService.createIngredientsItems(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItems>updateIngredientStock(@PathVariable Long id)throws Exception{
        IngredientsItems items = ingredientsService.updateStock(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItems>>getIngredientsItems(@PathVariable Long id)throws Exception{
        List<IngredientsItems> items = ingredientsService.findRestaurantsIngredientsItems(id);
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>>getRestaurantIngredientCategory(@PathVariable Long id)throws Exception{
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);

    }








}
