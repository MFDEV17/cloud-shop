package com.mfdev.productservice.service;

import com.mfdev.api.core.product.dto.ProductCreateDto;
import com.mfdev.api.core.product.dto.ProductUpdateDto;
import com.mfdev.api.core.product.dto.ShortProductDto;
import com.mfdev.api.core.product.dto.mapper.ProductMapper;
import com.mfdev.api.core.product.service.ProductService;
import com.mfdev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper = ProductMapper.PRODUCT_MAPPER;

    @Override
    public Mono<ResponseEntity<String>> createProduct(ProductCreateDto product) {
        return Mono.fromCallable(() -> {
            try {
                repository.save(mapper.productCreateDtoToProduct(product));
                return ResponseEntity.ok("Product has been created");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResponseEntity<String>> deleteProductByUUID(UUID uuid) {
        return Mono.fromCallable(() -> {
            try {
                repository.deleteByProductUUID(uuid);
                return ResponseEntity.ok("Product has been deleted");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResponseEntity<String>> deleteProductById(Long id) {
        return Mono.fromCallable(() -> {
            try {
                repository.deleteById(id);
                return ResponseEntity.ok("Product has been deleted");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResponseEntity<String>> updateProduct(ProductUpdateDto dto) {
        return null;
    }

    @Override
    public Flux<ShortProductDto> findAllProducts(Pageable pageable) {
        return Flux.fromIterable(mapper.productsToShortProductsDtoListPageable(repository.findAll(), pageable));
    }

    @Override
    public Flux<ShortProductDto> findAllProducts() {
        List<ShortProductDto> shortProductDtos =
                mapper.productsToShortProductsDtoList(repository.findAll());
        return Flux.fromIterable(shortProductDtos);
    }

    @Override
    public Flux<ShortProductDto> findAllByName(String name) {
        return Flux.fromIterable(
                mapper.productsToShortProductsDtoList(repository.findAllByNameLike(name))
        );
    }

    @Override
    public Mono<ShortProductDto> findById(Long id) {
        return Mono.fromCallable(() -> mapper
                .productToShortProductDto(repository.getReferenceById(id)))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
