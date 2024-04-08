package com.easyskillup.sdoc.repository;

import com.easyskillup.sdoc.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> getCredentialByUserId(Long userId);
}
