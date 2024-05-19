package com.micropos.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.repository.CartRepository;
import com.micropos.cart.repository.ItemRepository;
import com.micropos.dto.CartDto;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartMapper cartMapper;

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    private final String COUNTER_URL = "http://WEBPOS-COUNTER/api/counter/";

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(Integer itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public Cart addItemToCart(Cart cart, Item item) {

        if (cart == null) {
            return null;
        }

        itemRepository.save(item);
        cart.addItem(item);
        cartRepository.save(cart);

        return cart;

    }

    public Cart addCart() {

        List<Item> items = new ArrayList<>();

        Cart cart = new Cart(null, items);
        cartRepository.save(cart);

        return cart;

    }

    public Cart deleteCart(Cart cart) {

        cartRepository.delete(cart);
        return cart;

    }

    public Cart deleteItemOfCart(Cart cart, Item item) {

        cart.removeItem(item);
        itemRepository.delete(item);
        cartRepository.save(cart);

        return cart;

    }

    public Double total(Cart cart) {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
    
        CartDto cartDto = cartMapper.toCartDto(cart);
        ObjectMapper objectMapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        class RequestContainer {
            HttpEntity<String> value;
        }

        final RequestContainer requestContainer = new RequestContainer();

        try {
            requestContainer.value = new HttpEntity<>(objectMapper.writeValueAsString(cartDto), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return circuitBreaker.run(() -> restTemplate.postForObject(COUNTER_URL+ "/checkout", requestContainer.value, Double.class),
                                throwable -> getDefaultTotal());

    }

    public Double getDefaultTotal() {
        return 0.0;
    }

    public Cart updateItemOfCart(Cart cart, Item oldItem, Item newItem) {

        cart.removeItem(oldItem);
        newItem.setId(oldItem.getId());
        newItem.setCartId(cart.getId());
        cart.addItem(newItem);

        itemRepository.save(newItem);
        cartRepository.save(cart);

        return cart;

    }
    
}
