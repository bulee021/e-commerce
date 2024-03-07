package com.xsis.ecommerce.Model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private int id;
    private int product_id;
    private int user_id;
    private Timestamp update_on;
    private boolean isDelete;
}
