package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.AddToCartDTO;
import com.marketplace.marketplace_backend.dto.CartItemResponseDTO;
import com.marketplace.marketplace_backend.dto.CartResponseDTO;
import com.marketplace.marketplace_backend.dto.UpdateCartItemDTO;
import com.marketplace.marketplace_backend.entity.Cart;
import com.marketplace.marketplace_backend.entity.CartItem;
import com.marketplace.marketplace_backend.entity.Product;
import com.marketplace.marketplace_backend.entity.User;
import com.marketplace.marketplace_backend.repository.CartItemRepository;
import com.marketplace.marketplace_backend.repository.CartRepository;
import com.marketplace.marketplace_backend.repository.ProductRepository;
import com.marketplace.marketplace_backend.repository.UserRepository;
import com.marketplace.marketplace_backend.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    //Add product to cart
    @Override
    public CartResponseDTO addToCart(AddToCartDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .user(user)
                                .cartItems(new ArrayList<>())
                                .build()
                ));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + dto.getQuantity());
        }else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build();
            cart.getCartItems().add(newItem);
        }

        cartRepository.save(cart);
        return buildCartResponse(cart);
    }


    //update cart item quantity
    @Override
    public CartResponseDTO updateCartItem(UpdateCartItemDTO dto) {

        CartItem item = cartItemRepository.findById(dto.getCartItemId())
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if(dto.getQuantity() <=0){
            cartItemRepository.delete(item);
        }else {
            item.setQuantity(dto.getQuantity());
            cartItemRepository.save(item);
        }

        return buildCartResponse(item.getCart());
    }


    //remove item from cart
    @Override
    public CartResponseDTO removeCartItem(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Cart cart = item.getCart();
        cartItemRepository.delete(item);

        return buildCartResponse(cart);
    }


    //get cart by user id
    @Override
    public CartResponseDTO getCartByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return buildCartResponse(cart);
    }


    //clear cart
    @Override
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteAll(cart.getCartItems());
    }


    // Helper method to build CartResponseDTO
    private CartResponseDTO buildCartResponse(Cart cart) {
        List<CartItemResponseDTO> items = new ArrayList<>();
        double total = 0.0;

        for(CartItem item : cart.getCartItems()){

            double subTotal = item.getProduct().getPrice() * item.getQuantity();
            total += subTotal;

            items.add(
                    CartItemResponseDTO.builder()
                            .cartItemId(item.getId())
                            .productId(item.getProduct().getId())
                            .productName(item.getProduct().getName())
                            .price(item.getProduct().getPrice())
                            .quantity(item.getQuantity())
                            .subTotal(subTotal)
                            .build()
            );
        }
        return CartResponseDTO.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .items(items)
                .totalAmount(total)
                .build();
    }
}
