package com.OnlineFoodOrdering.AdminController;

import com.OnlineFoodOrdering.Response.MessageResponse;
import com.OnlineFoodOrdering.model.Food;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.request.CreateFoodRequest;
import com.OnlineFoodOrdering.service.FoodService;
import com.OnlineFoodOrdering.service.RestaurantService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJWtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        Food food = foodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJWtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Successfully deleted food");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> UpdateFoodAvailability(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJWtToken(jwt);
      Food food = foodService.updateAvailabilityStatus(id);
      return new ResponseEntity<>(food, HttpStatus.OK);
    }


}
