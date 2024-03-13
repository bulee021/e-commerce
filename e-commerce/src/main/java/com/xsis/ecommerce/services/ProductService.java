package com.xsis.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.ecommerce.dto.InterProductDTO;
import com.xsis.ecommerce.dto.PostProductDTO;
import com.xsis.ecommerce.entities.ProductEntity;
import com.xsis.ecommerce.repositories.ProductRepository;
import com.xsis.ecommerce.utils.CustomException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository pr;

    public List<InterProductDTO> getAllProducts() {
        return pr.getAllProducts();
    }

    public void insertProduct(PostProductDTO postProductDTO) throws CustomException {
        if (pr.isNameExist(postProductDTO.getName())) {
            throw new CustomException(
                    453,
                    "Name " + postProductDTO.getName() + " is already exist");
        }

        ProductEntity dataProductEntity = new ProductEntity();
        dataProductEntity.setName(postProductDTO.getName());
        dataProductEntity.setDescription(postProductDTO.getDescription());
        dataProductEntity.setBrand(postProductDTO.getBrand());
        dataProductEntity.setModel(postProductDTO.getModel());
        dataProductEntity.setCategory(postProductDTO.getCategory());
        dataProductEntity.setStock(postProductDTO.getStock());
        dataProductEntity.setPrice(postProductDTO.getPrice());
        dataProductEntity.setImage_url(postProductDTO.getImage_url());

        pr.save(dataProductEntity);
    }

    public void updateProduct(PostProductDTO postProductDTO) throws CustomException {
        ProductEntity dataProductEntity = pr.getReferenceById(postProductDTO.getId_product());

        if (postProductDTO.getName().equals(dataProductEntity.getName())) {

        } else if (pr.isNameExist(postProductDTO.getName())) {
            throw new CustomException(
                    453,
                    "Name " + postProductDTO.getName() + " is already exist");
        }

        dataProductEntity.setId_product(postProductDTO.getId_product());
        dataProductEntity.setName(postProductDTO.getName());
        dataProductEntity.setDescription(postProductDTO.getDescription());
        dataProductEntity.setBrand(postProductDTO.getBrand());
        dataProductEntity.setModel(postProductDTO.getModel());
        dataProductEntity.setCategory(postProductDTO.getCategory());
        dataProductEntity.setStock(postProductDTO.getStock());
        dataProductEntity.setPrice(postProductDTO.getPrice());
        dataProductEntity.setImage_url(postProductDTO.getImage_url());

        pr.save(dataProductEntity);
    }

}
