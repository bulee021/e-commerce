package com.xsis.ecommerce.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.dto.PostProductDTO;
import com.xsis.ecommerce.services.ProductService;
import com.xsis.ecommerce.utils.CustomException;
import com.xsis.ecommerce.utils.Resp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService ps;

    @GetMapping("/product")
    public Resp<List<InterProductDTO>> getAllProducts() {
        Resp<List<InterProductDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setData(ps.getAllProducts());

        return response;
    }

    @PostMapping("/product")
    public Resp<String> insertProduct(
            @RequestBody PostProductDTO postProductDTO) {
        Resp<String> response = new Resp<>();

        try {
            ps.insertProduct(postProductDTO);
            response.setCode(200);
            response.setMessage("OK");

        } catch (CustomException e) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }

        return response;
    }

}
