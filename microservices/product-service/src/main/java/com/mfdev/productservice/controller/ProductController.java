package com.mfdev.productservice.controller;

import com.mfdev.api.core.product.dto.ProductCreateDto;
import com.mfdev.api.core.product.dto.ProductUpdateDto;
import com.mfdev.api.core.product.dto.ShortProductDto;
import com.mfdev.productservice.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl service;

    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createProduct(@RequestBody ProductCreateDto product) {
        return service.createProduct(product);
    }

    @DeleteMapping("/delete_uuid/{uuid}")
    public Mono<ResponseEntity<String>> deleteProductByUUID(@PathVariable UUID uuid) {
        return service.deleteProductByUUID(uuid);
    }

    @DeleteMapping("/delete_id/{id}")
    public Mono<ResponseEntity<String>> deleteProductById(@PathVariable Long id) {
        return service.deleteProductById(id);
    }

    public Mono<ResponseEntity<String>> updateProduct(ProductUpdateDto dto) {
        return null;
    }

    @GetMapping("/find_all")
    public Flux<ShortProductDto> findAllProducts() {
        return service.findAllProducts();
    }

    @GetMapping("/find_all/{name}")
    public Flux<ShortProductDto> findAllProductsByName(@PathVariable String name) {
        return service.findAllByName(name);
    }

    @GetMapping("/find_all/{offset}/{count}")
    public Flux<ShortProductDto> findAllProductsPageable(
            @PathVariable int offset,
            @PathVariable int count) {
        return service.findAllProductsPageable(offset, count);
    }

    @GetMapping("/find/{id}")
    public Mono<ShortProductDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
