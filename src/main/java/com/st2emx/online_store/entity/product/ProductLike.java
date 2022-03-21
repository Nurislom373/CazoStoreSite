package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.entity.authUser.AuthUser;
import com.st2emx.online_store.entity.base.Auditable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductLike extends Auditable {
    private Product product;
    private AuthUser authUser;
}
