package com.st2emx.online_store.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 28.03.2022 10:20
 * Project : CazoStoreSite-master
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private MultipartFile image_path;
}
