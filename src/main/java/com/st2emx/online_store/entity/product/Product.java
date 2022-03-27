package com.st2emx.online_store.entity.product;

import com.st2emx.online_store.dto.AuditableDto;
import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.category.Category;
import com.st2emx.online_store.entity.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AuditableDto {
    private Category category;
    private String name;
    private String description;
    private Double price;
    private Boolean is_new;
    private Status status;
}
