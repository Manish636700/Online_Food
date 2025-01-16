package com.OnlineFoodOrdering.serviceImp;

import com.OnlineFoodOrdering.Dto.RestaurantDto;
import com.OnlineFoodOrdering.model.Address;
import com.OnlineFoodOrdering.model.Restaurant;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.repository.AddressRepository;
import com.OnlineFoodOrdering.repository.RestaurantRepository;
import com.OnlineFoodOrdering.repository.UserRepository;
import com.OnlineFoodOrdering.request.CreateRestaurantRequest;
import com.OnlineFoodOrdering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);

    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null && updateRestaurant.getCuisineType()!=null)
        {
         restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }

        if(restaurant.getDescription()!=null && updateRestaurant.getDescription()!=null)
        {
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if(restaurant.getName()!=null && updateRestaurant.getName()!=null)
        {
            restaurant.setName(updateRestaurant.getName());
        }
        if(restaurant.getOpeningHours()!=null && updateRestaurant.getOpeningHours()!=null)
        {
            restaurant.setOpeningHours(updateRestaurant.getOpeningHours());
        }
        if(restaurant.getImages()!=null && updateRestaurant.getImages()!=null)
        {
            restaurant.setImages(updateRestaurant.getImages());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant == null)
        {
            throw new Exception("restaurant not found with id " +restaurantId);
        }
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long Id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(Id);
        if(restaurant.isEmpty())
        {
            throw new Exception("restaurant not found with id " + Id);
        }
        return restaurant.get();

    }

    @Override
    public Restaurant getRestaurantByUserId(Long UserId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(UserId);
        if(restaurant == null)
        {
            throw new Exception("restaurant not found with id " + UserId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        RestaurantDto existingFavorite = user.getFavorites().stream()
                .filter(favorite -> favorite.getId().equals(restaurantId))
                .findFirst()
                .orElse(null);

        if (existingFavorite != null) {
            user.getFavorites().remove(existingFavorite);
        } else {
            user.getFavorites().add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long Id) throws Exception {

        Restaurant restaurant = findRestaurantById(Id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);

    }
}
