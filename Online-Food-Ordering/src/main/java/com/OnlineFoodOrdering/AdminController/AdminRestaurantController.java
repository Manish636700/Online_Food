package com.OnlineFoodOrdering.AdminController;

import com.OnlineFoodOrdering.Response.MessageResponse;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.request.CreateRestaurantRequest;
import com.OnlineFoodOrdering.service.RestaurantService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant>createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJWtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant>updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{
        User user = userService.findUserByJWtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>DeleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{
        User user = userService.findUserByJWtToken(jwt);

        restaurantService.deleteRestaurant(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted");
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant>updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception{
        User user = userService.findUserByJWtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }


    @GetMapping("/user")
    public ResponseEntity<Restaurant>findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJWtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }
}
