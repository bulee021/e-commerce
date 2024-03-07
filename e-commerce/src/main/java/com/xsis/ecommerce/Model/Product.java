package com.xsis.ecommerce.Model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private String model;
    private String category;
    private int stock;
    private int price;
    private String image_url;
}
