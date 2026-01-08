package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.*;
import com.marketplace.marketplace_backend.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    //Add item to cart
    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestBody CartRequestDTO cartRequestDTO) {

        CartResponseDTO response = cartService.addToCart(
                new AddToCartDTO(
                        cartRequestDTO.getUserId(),
                        cartRequestDTO.getProductId(),
                        cartRequestDTO.getQuantity()
                )
        );
        return ResponseEntity.ok(response);
    }


    //Get cart by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponseDTO> getCartByUser(
            @PathVariable  Long userId){
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }


    //Update item quantity in cart
    @PutMapping("/update")
    public ResponseEntity<CartResponseDTO> updateCartItem(
            @RequestBody UpdateCartItemDTO dto){

        return ResponseEntity.ok(cartService.updateCartItem(dto));
    }


    //Remove item from cart
    @DeleteMapping("/remove")
    public ResponseEntity<CartResponseDTO> removeItem(
            @RequestBody RemoveCartItemDTO dto) {

        return ResponseEntity.ok(cartService.removeCartItem(dto.getCartItemId()));
    }


    //Clear cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

}
