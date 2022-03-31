package com.st2emx.online_store.dto.color;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ColorDto {
    private Long id;
    private String name;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
}
