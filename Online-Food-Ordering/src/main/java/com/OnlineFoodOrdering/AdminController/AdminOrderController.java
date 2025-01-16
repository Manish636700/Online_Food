package com.OnlineFoodOrdering.AdminController;

import com.OnlineFoodOrdering.model.Order;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.service.OrderService;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJWtToken(jwt);
        List<Order> orders = orderService.getRestaurantOrder(id, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJWtToken(jwt);
        Order orders = orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }




}
