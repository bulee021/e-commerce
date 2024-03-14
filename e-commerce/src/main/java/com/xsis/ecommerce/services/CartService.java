package com.xsis.ecommerce.services;

import com.xsis.ecommerce.entities.Cart;
import com.xsis.ecommerce.entities.ProductEntity;
import com.xsis.ecommerce.entities.User;
import com.xsis.ecommerce.repositories.CartRepository;
import com.xsis.ecommerce.repositories.ProductRepository;
import com.xsis.ecommerce.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void addToCart(Long productId, Long userId, Integer quantity) throws CustomException {

        //CHECK IF PRODUCT IN PRODUCT DATABASE
        ProductEntity product = productRepository.getProductNewById(productId);
        if(product != null){
            //CHECK IF STOCK PRODUCT IS AVAILABLE
            if(product.getStock() != 0){
                //CHECK IF PRODUCT IN USER CART
                Cart cart = cartRepository.findCartByProductIdAndUserId(productId, userId);
                //IF NOT IN CART DATABASE
                if(cart == null){
                    Cart cartEntity = new Cart();
                    cartEntity.setProduct(product);
                    cartEntity.setUser(new User(userId));
                    cartEntity.setQuantity(quantity);
                    cartEntity.setAmount(product.getPrice() * quantity);
                    cartEntity.setUpdate_on(new Timestamp(System.currentTimeMillis()));
                    product.setStock(product.getStock() - 1);
                    cartRepository.save(cartEntity);
                    productRepository.save(product);
                }else{
                    cart.setQuantity(cart.getQuantity() + quantity);
                    cart.setAmount(cart.getAmount() * cart.getQuantity());
                    cart.setUpdate_on(new Timestamp(System.currentTimeMillis()));
                    product.setStock(product.getStock() - 1);
                    cartRepository.save(cart);
                    productRepository.save(product);
                }
            }else{
                throw new CustomException(1, "STOCK NOT AVAILABLE");
            }
        }else{
            throw new CustomException(1, "PRODUCT NOT AVAILABLE");
        }
    }

    public List<Cart> findCartByUserId(Long user_id) {
        return cartRepository.findCartByUserId(user_id);
    }

    public List<Cart> findAllCart(){
        return cartRepository.findAllCart();
    }

    public void updateQuantity(Integer quantity, Long userId, Long productId) throws CustomException{
        //CHECK IF PRODUCT ID AND USER ID IN CART DATABASE
        Cart cart = cartRepository.findCartByProductIdAndUserId(productId, userId);
        //GET PRODUCT TO GET PRODUCT PRICE
        ProductEntity product = productRepository.getProductNewById(productId);
        if(cart != null){
            cart.setQuantity(quantity);
            cart.setAmount(product.getPrice() * cart.getQuantity());
            cart.setUpdate_on(new Timestamp(System.currentTimeMillis()));
            cartRepository.save(cart);
        }else{
            throw new CustomException(1, "BAD REQUEST");
        }
    }

    public void deleteCartProductByUserId(Long productId, Long userId) throws CustomException {
        //CHECK IF PRODUCT ID AND USER ID IN CART DATABASE
        Cart cart = cartRepository.findCartByProductIdAndUserId(productId, userId);
        if(cart != null){
            cart.setIs_delete(true);
            cartRepository.save(cart);
        }else{
            throw new CustomException(1, "BAD REQUEST");
        }
    }
}
