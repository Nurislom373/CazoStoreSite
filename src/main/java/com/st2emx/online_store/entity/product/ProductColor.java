package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.color.Color;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductColor extends Auditable {
    private Product product;
    private Color color;
}
