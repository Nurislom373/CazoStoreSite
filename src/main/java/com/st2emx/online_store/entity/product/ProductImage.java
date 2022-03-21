package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends Auditable {
    private Product product;
    private String image_path;
    private Boolean is_main;
}
