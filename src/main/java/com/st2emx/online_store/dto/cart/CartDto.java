package com.st2emx.online_store.dto.cart;

import com.st2emx.online_store.dto.AuditableDto;
import com.st2emx.online_store.dto.productImage.ImagePathDto;
import com.st2emx.online_store.entity.product.Product;
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
public class CartDto extends AuditableDto {
    private Product product;
    private ImagePathDto image;
}
