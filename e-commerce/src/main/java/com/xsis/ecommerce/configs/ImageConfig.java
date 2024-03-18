package com.xsis.ecommerce.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // path lokasi folder uploads
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        path += "\\e-commerce\\uploads\\";
        // path += "\\uploads\\";

        // tambah fungsi jika membuka localhost/images/...
        // nanti membuka file dari folder uploads
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + path);

        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
