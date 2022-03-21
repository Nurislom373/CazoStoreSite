package com.st2emx.online_store.entity.status;

import com.st2emx.online_store.dto.AuditableDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status extends AuditableDto {
    private String name;
    private String code;
}
