package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.AddToCartDTO;
import com.marketplace.marketplace_backend.dto.CartResponseDTO;
import com.marketplace.marketplace_backend.dto.UpdateCartItemDTO;

public interface CartService {

    //Add product to cart
    CartResponseDTO addToCart(AddToCartDTO dto);

    //update cart item quantity
    CartResponseDTO updateCartItem(UpdateCartItemDTO dto);

    //remove item from cart
    CartResponseDTO removeCartItem(Long cartItemId);

    //get cart by user id
    CartResponseDTO getCartByUser(Long userId);

    //clear cart
    void clearCart(Long userId);
}
