package com.xsis.ecommerce.entities;

import com.xsis.ecommerce.dto.InterProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Entity(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseProperties{

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @JoinColumn
    @ManyToOne
    private ProductEntity product;

    @JoinColumn
    @ManyToOne
    private User user;

    @Column
    private Integer quantity;

    @Column
    private Long amount;

    @Column
    private Timestamp update_on;
}
