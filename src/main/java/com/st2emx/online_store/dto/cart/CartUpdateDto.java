package com.st2emx.online_store.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * created by Elyor Ergashov
 *
 * @Author : elyor
 * @Date : 29/03/22
 * @Project : CazoStoreSite-master
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartUpdateDto /*extends AuditableDto*/ {
    private Long id;
//    private Long productId;
    private int count;


}
