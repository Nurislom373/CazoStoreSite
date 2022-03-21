package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.size.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSize extends Auditable {
    private Product product;
    private Size size;
}
