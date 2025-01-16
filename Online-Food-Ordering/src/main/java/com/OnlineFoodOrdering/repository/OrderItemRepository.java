package com.OnlineFoodOrdering.repository;

import com.OnlineFoodOrdering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{


}
