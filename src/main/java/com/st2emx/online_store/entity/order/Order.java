package com.st2emx.online_store.entity.order;

import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.userCart.AuthUserCart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends Auditable {
    private AuthUserCart authUserCart;
    private Boolean is_success;
}
