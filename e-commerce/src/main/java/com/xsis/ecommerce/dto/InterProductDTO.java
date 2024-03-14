package com.xsis.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface InterProductDTO {
    Long getId_product();

    String getName();

    String getDescription();

    String getBrand();

    String getModel();

    String getCategory();

    Integer getStock();

    Long getPrice();

    String getImage_url();

}
