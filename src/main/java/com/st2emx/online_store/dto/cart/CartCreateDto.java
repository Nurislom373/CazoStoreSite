package com.st2emx.online_store.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * created by Elyor Ergashov
 *
 * @Author : elyor
 * @Date : 25/03/22
 * @Project : CazoStoreSite-master
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateDto {
    private Long sizeId;
    private Long colorId;
    private Integer count;
    private Boolean accept;
}
