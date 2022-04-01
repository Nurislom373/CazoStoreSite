package com.st2emx.online_store.dto.size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SizeCreateDto {
    private String code;
    private Integer number;
}
