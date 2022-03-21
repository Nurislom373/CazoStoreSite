package com.st2emx.online_store.dto.token;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
