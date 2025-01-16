import React from "react";

import { Button, Card } from "@mui/material";

export const OrderCard = ({item,order}) => {
  return (
    <Card className="flex justify-between items-center p-5">
      <div className="flex items-center space-x-5">
        <img
          className="h-16 w-16"
          src={item.food.images}
          alt="image_not_loading"
        />
        <div>
          <p className="text-left">{item.food.name}</p>
          <p className="text-left">₹{item.totalPrice}</p> 
        </div>
      </div>
      <div>
        <Button variant="contained" className="cursor-not-allowed"> {order.orderStatus} </Button>
      </div>
    </Card>
  );
};
