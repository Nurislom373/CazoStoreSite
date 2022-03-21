package com.st2emx.online_store.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditableDto {
    private Long id;
    private Long createdBy;
    private String createdAt;
    private Long updatedBy;
    private String updatedAt;
    private boolean deleted;
}
