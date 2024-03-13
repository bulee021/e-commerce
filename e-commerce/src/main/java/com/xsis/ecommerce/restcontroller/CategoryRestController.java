package com.xsis.ecommerce.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.repositories.ProductRepository;
import com.xsis.ecommerce.utils.Resp;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private ProductRepository pr;

    @GetMapping("/category")
    public Resp<List<InterProductDTO>> getAllCategories() {
        Resp<List<InterProductDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setData(pr.getAllCategories());
        return response;
    }

}
