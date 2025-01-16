package com.OnlineFoodOrdering.repository;

import com.OnlineFoodOrdering.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
