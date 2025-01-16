package com.OnlineFoodOrdering.controller;

import com.OnlineFoodOrdering.model.Food;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.service.FoodService;
import com.OnlineFoodOrdering.service.RestaurantService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

 @GetMapping("/search")
 public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                              @RequestHeader("Authorization") String jwt) throws Exception
 {
     User user = userService.findUserByJWtToken(jwt);

     List<Food> foods = foodService.searchFood(name);

     return new ResponseEntity<>(foods, HttpStatus.CREATED);
 }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam (required = false) boolean Vegetarian,
                                                        @RequestParam (required = false) boolean Seasonal,
                                                        @RequestParam (required = false) boolean nonveg,
                                                 @RequestParam(required = false) String food_category,
                                                 @PathVariable Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user = userService.findUserByJWtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId,Vegetarian,Seasonal,nonveg,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }




}
