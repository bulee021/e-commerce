package com.xsis.ecommerce.restcontrollers;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.dto.PostProductDTO;
import com.xsis.ecommerce.entities.Cart;
import com.xsis.ecommerce.services.CartService;
import com.xsis.ecommerce.utils.CustomException;
import com.xsis.ecommerce.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public Resp<String> addToCart(@RequestParam("productId") Long productId,
                                  @RequestParam("userId") Long userId,
                                  @RequestParam("quantity") Integer quantity){
        Resp<String> response = new Resp<>();

        try{
            cartService.addToCart(productId, userId, quantity);
            response.setCode(200);
            response.setMessage("OK");
        }catch (CustomException e){
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/get-cart-by-user-id")
    public Resp<List<Cart>> getCartByUserId(@RequestParam("user_id") Long userId) {
        Resp<List<Cart>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setData(cartService.findCartByUserId(userId));

        return response;
    }

    @GetMapping("/find-all-cart")
    public Resp<List<Cart>> findAllCart() {
        Resp<List<Cart>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");

        List<Cart> cart = cartService.findAllCart();
        response.setTotal_data(cart.size());
        response.setData(cart);

        return response;
    }

    @PutMapping("/update-quantity")
    public Resp<String> updateQuantityProductByUserIdAndProductId(@RequestParam("quantity") Integer quantity,
                                                                  @RequestParam("productId") Long productId,
                                                                  @RequestParam("userId") Long userId) throws CustomException {

        Resp<String> response = new Resp<>();
        try {
            cartService.updateQuantity(quantity, productId, userId);
            response.setCode(200);
            response.setMessage("Update quantity to " + quantity + " with productId " + productId + " and userId " + userId);
        }catch (CustomException e){
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete-product-cart-by-user-id")
    public Resp<String> deleteCartProductByUserId(@RequestParam("productId") Long productId,
                                                  @RequestParam("userId") Long userId) throws CustomException {

        Resp<String> response = new Resp<>();
        try {
            cartService.deleteCartProductByUserId(productId, userId);
            response.setCode(200);
            response.setMessage("Delete productId " + productId + " with userId " + userId);
        }catch (CustomException e){
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
