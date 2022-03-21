package com.st2emx.online_store.entity.authUser;

import com.st2emx.online_store.entity.base.Auditable;
import com.st2emx.online_store.entity.language.Language;
import com.st2emx.online_store.entity.role.Role;
import com.st2emx.online_store.entity.status.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private Language language;

    private Role role;

    private Status status;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Long id, Long createdBy, Long updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String username, String password, String firstName, String lastName, String phone, Language language, Role role, Status status) {
        super(id, createdBy, updatedBy, createdAt, updatedAt, deleted);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.language = language;
        this.role = role;
        this.status = status;
    }
}

