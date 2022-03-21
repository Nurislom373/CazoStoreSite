package com.st2emx.online_store.entity.color;

import com.st2emx.online_store.entity.base.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Color extends Auditable {
    private String name;
}
