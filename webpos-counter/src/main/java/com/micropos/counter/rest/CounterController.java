package com.micropos.counter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micropos.api.CounterApi;
import com.micropos.counter.service.CounterService;
import com.micropos.dto.CartDto;

@RestController
@RequestMapping("api")
public class CounterController implements CounterApi {

    @Autowired
    private CounterService counterService;

    @Override
    public ResponseEntity<Double> checkout(CartDto cartDto) {

        return ResponseEntity.ok(counterService.getTotal(cartDto));

    }
    
}
