package com.OnlineFoodOrdering.controller;


import com.OnlineFoodOrdering.Dto.RestaurantDto;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.service.RestaurantService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>>searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception {
        User user = userService.findUserByJWtToken(jwt);

        List<Restaurant>restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>>getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJWtToken(jwt);

        List<Restaurant>restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant>findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJWtToken(jwt);

        Restaurant restaurants = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJWtToken(jwt);

        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);


    }

}
