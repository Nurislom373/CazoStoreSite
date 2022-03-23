package com.st2emx.online_store.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private MultipartFile file;
}
