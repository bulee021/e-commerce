package com.xsis.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseProperties {

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean is_delete = false;

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
