package com.st2emx.online_store.dto.productColor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorUpdateDto {
    private Long id;
    private Long productId;
    private Long colorId;
}
