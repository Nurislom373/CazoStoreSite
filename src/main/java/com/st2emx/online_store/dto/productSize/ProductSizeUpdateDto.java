package com.st2emx.online_store.dto.productSize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeUpdateDto {
    private Long productId;
    private Long SizeId;
}
