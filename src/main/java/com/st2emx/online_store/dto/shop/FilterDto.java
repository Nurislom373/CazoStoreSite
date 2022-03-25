package com.st2emx.online_store.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
    private Long sortById;
    private Long priceId;
    private Long colorId;
}
