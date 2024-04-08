package com.easyskillup.sdoc.repository;

import com.easyskillup.sdoc.entities.Confirmation;
import com.easyskillup.sdoc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Optional<Confirmation> findByKey(String key);
    Optional<Confirmation> findByUser(User user);
}
