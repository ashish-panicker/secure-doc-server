package com.easyskillup.sdoc.builders;

import com.easyskillup.sdoc.entities.Role;
import com.easyskillup.sdoc.entities.User;

import java.util.UUID;

import static java.time.LocalDateTime.*;
import static org.apache.commons.lang3.StringUtils.*;

public class UserBuilder {

    public static User createUser(String firstName, String lastName, String email, Role role){
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .lastLogin(now())
                .accountEnabled(false)
                .accountExpired(false)
                .accountLocked(true)
                .loginAttempt(0)
                .qrCodeSecret(EMPTY)
                .bio(EMPTY)
                .phone(EMPTY)
                .imageUrl("https://cdn-icons-png.flaticon.com/512/149/149071.png")
                .roles(role)
                .build();
    }
}
