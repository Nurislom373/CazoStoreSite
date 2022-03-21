package com.st2emx.online_store.dto.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {
    private Long id;
    private String name;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
}
