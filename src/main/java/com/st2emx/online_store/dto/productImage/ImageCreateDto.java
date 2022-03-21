package com.st2emx.online_store.dto.productImage;

import com.st2emx.online_store.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageCreateDto  {
    private Product product;
    private String image_path;
    private Boolean is_main;

}
