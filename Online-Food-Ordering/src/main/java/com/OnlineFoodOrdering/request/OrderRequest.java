package com.OnlineFoodOrdering.request;


import com.OnlineFoodOrdering.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long RestaurantId;

    private Address address;


}
