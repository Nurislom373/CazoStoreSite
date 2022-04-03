package com.st2emx.online_store.dto.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private Long categoryId;
    private String name;
    private String description;
    private Double price;
    private MultipartFile image;
}
