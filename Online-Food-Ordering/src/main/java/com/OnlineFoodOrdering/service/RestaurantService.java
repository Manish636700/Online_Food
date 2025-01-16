package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.Dto.RestaurantDto;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {


    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updateRestaurant)throws Exception;

    public void deleteRestaurant(Long restaurantId)throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long Id) throws Exception;

    public Restaurant getRestaurantByUserId(Long UserId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long Id) throws Exception;
}
