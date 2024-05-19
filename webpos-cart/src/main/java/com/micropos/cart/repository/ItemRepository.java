package com.micropos.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micropos.cart.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
    
}
