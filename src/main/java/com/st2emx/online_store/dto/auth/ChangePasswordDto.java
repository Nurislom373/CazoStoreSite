package com.st2emx.online_store.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 31.03.2022 21:24
 * Project : online_store
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    private Long id;
    private String password;
    private String newPassword;
    private String renewPassword;

}
