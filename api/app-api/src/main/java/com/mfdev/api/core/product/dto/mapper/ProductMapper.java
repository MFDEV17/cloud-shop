package com.mfdev.api.core.product.dto.mapper;

import com.mfdev.api.core.product.dto.ProductCreateDto;
import com.mfdev.api.core.product.dto.ShortProductDto;
import com.mfdev.api.core.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productUUID", ignore = true)
    Product productCreateDtoToProduct(ProductCreateDto dto);

    ShortProductDto productToShortProductDto(Product product);

    default List<ShortProductDto> productsToShortProductsDtoList(List<Product> products) {
        return products.stream()
                .map(this::productToShortProductDto)
                .collect(Collectors.toList());
    }

    default List<ShortProductDto> productsToShortProductsDtoListPageable(Page<Product> products) {
        return productsToShortProductsDtoList(products.getContent());
    }
}
