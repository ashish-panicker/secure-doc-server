package com.easyskillup.sdoc.service;

import com.easyskillup.sdoc.entities.Role;

public interface UserService {

    void createUser(String firstName, String lastName, String email, String password);

    Role getRoleName(String name);

    void verifyAccountKey(String token);
}
