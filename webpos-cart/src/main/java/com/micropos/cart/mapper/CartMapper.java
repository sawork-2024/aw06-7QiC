package com.micropos.cart.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.dto.CartDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemFieldsDto;
import com.micropos.dto.ProductDto;

@Mapper
public interface CartMapper {

    List<CartDto> toCartDtos(Collection<Cart> carts);

    List<Cart> toCarts(Collection<CartDto> cartDtos);

    default Item toItem(ItemFieldsDto itemFieldsDto) {

        Item item = new Item();
        item.setAmount(itemFieldsDto.getAmount());
        item.setProductId(itemFieldsDto.getProduct().getId());
        item.setProductImage(itemFieldsDto.getProduct().getImage());
        item.setProductName(itemFieldsDto.getProduct().getName());
        item.setProductPrice(itemFieldsDto.getProduct().getPrice());
    
        return item;

    }

    default Cart toCart(CartDto cartDto) {

        return new Cart(cartDto.getId(), toItems(cartDto.getItems(), cartDto));

    }

    default CartDto toCartDto(Cart cart) {

        return new CartDto().id(cart.getId())
                    .items(toItemDtos(cart.getItems()));

    }

    default List<Item> toItems(Collection<ItemDto> itemDtos, CartDto cartDto) {

        List<Item> items = new ArrayList<>();

        for (ItemDto itemDto : itemDtos) {
            items.add(toItem(itemDto, cartDto));
        }

        return items;

    }

    default List<ItemDto> toItemDtos(Collection<Item> items) {

        List<ItemDto> itemDtos = new ArrayList<>();

        for (Item item : items) {
            itemDtos.add(toItemDto(item));
        }

        return itemDtos;

    }

    default Item toItem(ItemDto itemDto, CartDto cartDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setCartId(cartDto.getId());
        item.setProductId(itemDto.getProduct().getId());
        item.setProductName(itemDto.getProduct().getName());
        item.setProductPrice(itemDto.getProduct().getPrice());
        item.setProductImage(itemDto.getProduct().getImage());
        item.setAmount(itemDto.getAmount());
        return item;
    }
    
    default ItemDto toItemDto(Item item) {
        return new ItemDto().id(item.getId())
                    .amount(item.getAmount())
                    .product(getProductDto(item));
    }

    default ProductDto getProductDto(Item item) {
        return new ProductDto().id(item.getProductId())
                    .image(item.getProductImage())
                    .name(item.getProductName())
                    .price(item.getProductPrice());
    }

}
