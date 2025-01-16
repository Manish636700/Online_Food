package com.OnlineFoodOrdering.controller;

import com.OnlineFoodOrdering.model.Order;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.request.OrderRequest;
import com.OnlineFoodOrdering.service.OrderService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order>createOrder(@RequestBody OrderRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJWtToken(jwt);
        Order order = orderService.createOrder(req, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping("/order/user")
    public ResponseEntity<List<Order>>getOrderHistory(
                                                  @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJWtToken(jwt);
       List<Order> orders = orderService.getUsersOrder(user.getId());
       return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
