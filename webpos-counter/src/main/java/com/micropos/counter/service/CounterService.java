package com.micropos.counter.service;

import org.springframework.stereotype.Service;

import com.micropos.dto.CartDto;

@Service
public class CounterService {

    public Double getTotal(CartDto cartDto) {

        return cartDto.getItems().stream()
                .mapToDouble(itemDto -> itemDto.getProduct().getPrice() * itemDto.getAmount())
                .sum();
                
    }
    
}
