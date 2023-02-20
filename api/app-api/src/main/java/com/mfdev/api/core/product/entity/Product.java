package com.mfdev.api.core.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.UUID.randomUUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"description", "price", "name"})
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NaturalId
    private UUID productUUID = randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
}
