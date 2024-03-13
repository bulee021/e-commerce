package com.xsis.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

        @Query(nativeQuery = true, value = "select \r\n" + //
                        "name,\r\n" + //
                        "model,\r\n" + //
                        "description,\r\n" + //
                        "category,\r\n" + //
                        "brand,\r\n" + //
                        "image_url,\r\n" + //
                        "price,\r\n" + //
                        "id_product,\r\n" + //
                        "stock\r\n" + //
                        "from product\r\n" + //
                        "where is_delete = false \r\n")
        public List<InterProductDTO> getAllProducts();

        @Query(nativeQuery = true, value = "select exists(\r\n" + //
                        "\tselect * \r\n" + //
                        "\tfrom product \r\n" + //
                        "\twhere \r\n" + //
                        "\tname ilike :name \r\n" + //
                        "\tand is_delete = false)")
        public Boolean isNameExist(@Param("name") String name);

        @Query(nativeQuery = true, value = "select \r\n" + //
                        "name, \r\n" + //
                        "model, \r\n" + //
                        "description, \r\n" + //
                        "category, \r\n" + //
                        "brand, \r\n" + //
                        "image_url, \r\n" + //
                        "price, \r\n" + //
                        "id_product, \r\n" + //
                        "stock \r\n" + //
                        "from product\r\n" + //
                        "where is_delete = false \r\n" + //
                        "and id_product = :id_product")
        public InterProductDTO getProductById(@Param("id_product") Long id_product);
}
