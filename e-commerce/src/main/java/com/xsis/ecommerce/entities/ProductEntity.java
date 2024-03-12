package com.xsis.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseProperties {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String category;

    @Column
    private Integer stock;

    @Column
    private Long price;

    @Column
    private String image_url;

}
