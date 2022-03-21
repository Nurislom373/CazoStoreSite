package com.st2emx.online_store.dto.product;

import com.st2emx.online_store.entity.category.Category;
import com.st2emx.online_store.entity.status.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private Category category;
    private String name;
    private String description;
    private Double price;
    private Boolean is_new;
    private Status status;
    private String image_path;
}
