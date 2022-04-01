package com.st2emx.online_store.dto.size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SizeDto {
    private Long id;
    private String code;
    private Integer number;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
}
