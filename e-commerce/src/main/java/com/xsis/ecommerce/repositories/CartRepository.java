package com.xsis.ecommerce.repositories;

import com.xsis.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM cart where is_delete = false")
    public List<Cart> findAllCart();

    @Query(nativeQuery = true, value = "select * from cart " +
            "where product_id_product = :product_id_product and user_id_user = :user_id_user and is_delete = false")
    public Cart findCartByProductIdAndUserId(@Param("product_id_product") Long product_id_product, @Param("user_id_user") Long user_id);

    @Query(nativeQuery = true, value = "select * from cart " +
            "where user_id_user = :user_id_user and is_delete = false")
    public List<Cart> findCartByUserId(@Param("user_id_user") Long user_id);

}
