package com.OnlineFoodOrdering.serviceImp;

import com.OnlineFoodOrdering.model.Cart;
import com.OnlineFoodOrdering.model.CartItem;
import com.OnlineFoodOrdering.model.Food;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.repository.CartItemRepository;
import com.OnlineFoodOrdering.repository.CartRepository;
import com.OnlineFoodOrdering.request.AddCartItemRequest;
import com.OnlineFoodOrdering.service.CartService;
import com.OnlineFoodOrdering.service.FoodService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJWtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItem())
        {
            if(cartItem.getFood().equals(food))
            {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }


        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem>cartItemOptional = cartItemRepository.findById(cartItemId);

        if(cartItemOptional.isEmpty())
        {
            throw new Exception("CartItem not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);

        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJWtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem>cartItemOptional = cartItemRepository.findById(cartItemId);

        if(cartItemOptional.isEmpty())
        {
            throw new Exception("CartItem not found");
        }

        CartItem item = cartItemOptional.get();
        cart.getItem().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total=0L;
        for(CartItem cartItem : cart.getItem())
        {
            total += cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isEmpty())
        {
            throw new Exception("CartItem not found");
        }

        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        //User user = userService.findUserByJWtToken(jwt);
       Cart cart = cartRepository.findByCustomerId(userId);
       cart.setTotal(calculateCartTotals(cart));
       return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        //User user = userService.findUserByJWtToken(jwt);
        Cart cart = findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
