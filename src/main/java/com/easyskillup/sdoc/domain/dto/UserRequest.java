package com.easyskillup.sdoc.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequest(
        @NotEmpty(message = "First name cannot be empty")
        String firstName,
        @NotEmpty(message = "Last name cannot be empty")
        String lastName,
        @NotEmpty(message = "Email cannot be empty")
        @Email(message = "Email must be in proper format")
        String email,
        @NotEmpty(message = "Password cannot be empty") String password,
        String bio,
        String phone,
        String imageUrl
) {
}
