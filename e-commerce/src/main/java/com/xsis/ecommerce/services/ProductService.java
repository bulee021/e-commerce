package com.xsis.ecommerce.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    public InterProductDTO getProductById(Long id_product) {
        return pr.getProductById(id_product);
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
            // nama tetap sama, kolom lain yang berubah
        } else if (pr.isNameExist(postProductDTO.getName())) {
            // nama berubah -> harus dicek dengan nama yang lain agar tidak duplikat
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

    public void deleteProduct(Long id_product) {
        ProductEntity dataProductEntity = pr.getReferenceById(id_product);
        dataProductEntity.setIs_delete(true);

        pr.save(dataProductEntity);
    }

    public String uploadProductImage(
            Long id_product,
            MultipartFile file)
            throws IOException {

        // path lokasi folder uploads
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        path += "\\e-commerce\\uploads\\";

        // System.out.println(path);

        // path baru dengan nama file
        String fileName = "image-product-" + id_product + ".jpg";
        Path newPath = Paths.get(path + fileName);

        // memindahkan gambar ke folder upload
        Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

        // mengambil URL hasil upload
        String hasilUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/").path(fileName).toUriString();

        // ambil dulu data user
        ProductEntity productEntity = pr.getReferenceById(id_product);
        // ambil data biodata sebelumnya
        productEntity.setImage_url(hasilUpload.replace("http://localhost", ""));
        // simpan data image url
        pr.save(productEntity);

        return hasilUpload;
    }
}
