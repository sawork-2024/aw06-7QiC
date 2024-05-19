package com.micropos.cart.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micropos.api.CartsApi;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.service.CartService;
import com.micropos.dto.CartDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemFieldsDto;

@RestController
@RequestMapping("api")
public class CartController implements CartsApi {
    
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    @Override
    public ResponseEntity<CartDto> addItemToCart(Integer cartId, ItemFieldsDto itemFieldsDto) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        Item item = cartMapper.toItem(itemFieldsDto);
        item.setCartId(cartId);

        cart = cartService.addItemToCart(cart, item);

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

    @Override
    public ResponseEntity<CartDto> createCart() {

        Cart cart = cartService.addCart();

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

    @Override
    public ResponseEntity<CartDto> deleteCart(Integer cartId) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        cart = cartService.deleteCart(cart);

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

    @Override
    public ResponseEntity<CartDto> deleteItemOfCart(Integer cartId, Integer itemId) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Item> oitem = cart.getItems().stream()
                                    .filter(item -> item.getId().equals(itemId))
                                    .findFirst();
        
        if (!oitem.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Item item = oitem.get();
        cart = cartService.deleteItemOfCart(cart, item);

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

    @Override
    public ResponseEntity<List<CartDto>> listCarts() {

        List<Cart> carts = cartService.getCarts();
        if (carts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartMapper.toCartDtos(carts));

    }

    @Override
    public ResponseEntity<CartDto> showCartById(Integer cartId) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

    @Override
    public ResponseEntity<ItemDto> showItemOfCart(Integer cartId, Integer itemId) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Item> oitem = cart.getItems().stream()
                                .filter(item -> item.getId().equals(itemId))
                                .findFirst();
        if (!oitem.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Item item = oitem.get();
        return ResponseEntity.ok(cartMapper.toItemDto(item));

    }

    @Override
    public ResponseEntity<Double> showTotal(Integer cartId) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartService.total(cart));

    }

    public ResponseEntity<CartDto> updateItemOfCart(Integer cartId, Integer itemId, ItemFieldsDto itemFieldsDto) {

        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Item> oitem = cart.getItems().stream()
                                    .filter(item -> item.getId().equals(itemId))
                                    .findFirst();
        if (!oitem.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Item olditem = oitem.get();
        Item newitem = cartMapper.toItem(itemFieldsDto);
        cart = cartService.updateItemOfCart(cart, olditem, newitem);

        return ResponseEntity.ok(cartMapper.toCartDto(cart));

    }

}
