package com.mfdev.api.core.product.service;

import com.mfdev.api.core.product.dto.ProductCreateDto;
import com.mfdev.api.core.product.dto.ProductUpdateDto;
import com.mfdev.api.core.product.dto.ShortProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ResponseEntity<String>> createProduct(ProductCreateDto product);

    Mono<ResponseEntity<String>> deleteProductByUUID(UUID uuid);

    Mono<ResponseEntity<String>> deleteProductById(Long id);

    Mono<ResponseEntity<String>> updateProduct(ProductUpdateDto dto);

    Flux<ShortProductDto> findAllProducts(Pageable pageable);

    Flux<ShortProductDto> findAllProducts();

    Flux<ShortProductDto> findAllByName(String name);

    Mono<ShortProductDto> findById(Long id);
}
