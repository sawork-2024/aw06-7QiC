package com.micropos.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micropos.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
