package com.mfdev.productservice.repository;

import com.mfdev.api.core.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByProductUUID(UUID uuid);
    List<Product> findAllByNameLike(String name);
}
