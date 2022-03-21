package com.st2emx.online_store.dto.product;

import com.st2emx.online_store.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 15.03.2022 20:25
 * Project : onlineMagazin
 */
@Getter
@Setter
public class ProductUpdateDto {
    private Category category;
    private String name;
    private String description;
    private Double price;
}
