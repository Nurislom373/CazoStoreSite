package com.st2emx.online_store.dto.product;
import com.st2emx.online_store.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCreateDto {
    private Category category;
    private String name;
    private String description;
    private Double price;
}
