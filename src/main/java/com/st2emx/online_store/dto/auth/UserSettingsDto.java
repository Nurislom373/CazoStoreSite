package com.st2emx.online_store.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 31.03.2022 21:10
 * Project : online_store
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingsDto {
    private Long id;
    private String language_code;
    private String price_type;
}

