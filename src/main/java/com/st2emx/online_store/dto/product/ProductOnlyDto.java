package com.st2emx.online_store.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOnlyDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean is_new;
    private String image_path;
}
