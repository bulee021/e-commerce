package com.xsis.ecommerce.restcontrollers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.dto.PostProductDTO;
import com.xsis.ecommerce.repositories.ProductRepository;
import com.xsis.ecommerce.services.ProductService;
import com.xsis.ecommerce.utils.CustomException;
import com.xsis.ecommerce.utils.Resp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductService ps;

    @Autowired
    private ProductRepository pr;

    @GetMapping("/product")
    public Resp<List<InterProductDTO>> getAllProducts() {
        Resp<List<InterProductDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");

        List<InterProductDTO> products = ps.getAllProducts();
        response.setTotal_data(products.size());
        response.setData(products);

        return response;
    }

    @GetMapping("/product/id")
    public Resp<InterProductDTO> getProductById(@RequestParam("id_product") Long id_product) {
        Resp<InterProductDTO> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setData(ps.getProductById(id_product));

        return response;
    }

    @GetMapping("/product/category")
    public Resp<List<InterProductDTO>> getProductByCategory(@RequestParam("category") String category) {
        Resp<List<InterProductDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        response.setData(pr.getProductByCategory(category));

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

    @PutMapping("/product")
    public Resp<String> updateProduct(
            @RequestBody PostProductDTO postProductDTO) {

        Resp<String> response = new Resp<>();

        try {
            ps.updateProduct(postProductDTO);
            response.setCode(200);
            response.setMessage("OK");

        } catch (CustomException e) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/product")
    public Resp<String> deleteMapping(
            @RequestParam("id_product") Long id_product) {

        Resp<String> response = new Resp<>();

        ps.deleteProduct(id_product);
        response.setCode(200);
        response.setMessage("OK");

        return response;
    }

    @PostMapping("/product/image")
    public Resp<String> uploadProductImage(
            @RequestParam("id_product") Long id_product,
            @RequestParam("file") MultipartFile file) {

        Resp<String> response = new Resp<>();
        try {
            ps.uploadProductImage(id_product, file);
            response.setCode(200);
            response.setMessage("OK");

        } catch (IOException e) {
            e.printStackTrace();
            response.setCode(455);
            response.setMessage("Failed to upload file!");

        }

        return response;
    }
}
