package com.st2emx.online_store.config.session;

import com.st2emx.online_store.dto.token.TokenDto;
import org.apache.el.parser.Token;

public class SessionToken {
    public static TokenDto session;

    public static TokenDto getSession() {
        return session;
    }

    public static void setSession(TokenDto session) {
        SessionToken.session = session;
    }
}
