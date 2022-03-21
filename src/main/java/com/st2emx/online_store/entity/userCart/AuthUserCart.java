package com.st2emx.online_store.entity.userCart;

import com.st2emx.online_store.entity.authUser.AuthUser;
import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCart extends Auditable {
    private AuthUser authUser;
    private Product product;
    private Integer count;
    private Boolean accept;
}
