package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.entity.authUser.AuthUser;
import com.st2emx.online_store.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductComment extends Auditable {
    private Product product;
    private String message;
    private AuthUser authUser;
}
