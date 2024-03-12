package com.xsis.ecommerce.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//generic class
@JsonInclude(Include.NON_NULL)
public class Resp<T> {
    private Integer code = 200;
    private String message = "OK";
    private Integer total_data;
    private Integer total_page;
    private Integer page;
    private T data;

    public Resp() {
    }

    public Resp(CustomException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public Resp(T data) {
        this.data = data;
    }

    public Integer getTotal_data() {
        return this.total_data;
    }

    public void setTotal_data(Integer total_data) {
        this.total_data = total_data;
    }

    public Integer getTotal_page() {
        return this.total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
