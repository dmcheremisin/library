package com.example.util;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toEntity(ProductDto productDto) {
        Product entity = new Product();
        BeanUtils.copyProperties(productDto, entity);
        return entity;
    }
}
