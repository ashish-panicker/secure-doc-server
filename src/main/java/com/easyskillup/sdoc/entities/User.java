package com.easyskillup.sdoc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User extends Auditable {

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    private String firstName;

    private String lastName;

    @Column(unique = true, updatable = false, nullable = false)
    private String email;

    private int loginAttempt;

    private LocalDateTime lastLogin;

    private String phone;

    private String bio;

    private String imageUrl;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean accountEnabled;

    private boolean accountMultiFactorAuthEnabled;

    @JsonIgnore
    private String qrCodeSecret;

    @Column(columnDefinition = "text")
    private String qrCodeImageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Role roles;

}
