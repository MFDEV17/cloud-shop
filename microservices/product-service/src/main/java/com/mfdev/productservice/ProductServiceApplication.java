package com.mfdev.productservice;

import com.mfdev.productservice.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.mfdev.api"),
        @ComponentScan(basePackageClasses = ProductController.class)
})
@EntityScan("com.mfdev")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
