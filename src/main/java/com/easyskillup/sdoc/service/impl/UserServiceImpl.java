package com.easyskillup.sdoc.service.impl;

import com.easyskillup.sdoc.builders.UserBuilder;
import com.easyskillup.sdoc.entities.*;
import com.easyskillup.sdoc.events.EventType;
import com.easyskillup.sdoc.events.UserEvent;
import com.easyskillup.sdoc.exceptions.ApiException;
import com.easyskillup.sdoc.repository.ConfirmationRepository;
import com.easyskillup.sdoc.repository.CredentialRepository;
import com.easyskillup.sdoc.repository.RoleRepository;
import com.easyskillup.sdoc.repository.UserRepository;
import com.easyskillup.sdoc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConfirmationRepository confirmationRepository;
    private final CredentialRepository credentialRepository;
//    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var user = userRepository.save(createNewuser(firstName, lastName, email));
        credentialRepository.save(new Credential(user, password));
        var confirmation = confirmationRepository.save(new Confirmation(user));
        publisher.publishEvent(new UserEvent(user, EventType.REGISTER_ACCOUNT, Map.of("key", confirmation.getKey())));
    }

    @Override
    public Role getRoleName(String name) {
        return roleRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ApiException(String.format("%s role not found", name)));
    }

    @Override
    public void verifyAccountKey(String token) {
        var confirmation = confirmationRepository
                .findByKey(token)
                .orElseThrow(() -> new ApiException("Cannot find user corresponding to token " + token));
        var user = userRepository
                .findByEmailIgnoreCase(confirmation.getUser().getEmail())
                .orElseThrow(() -> new ApiException("Cannot find user corresponding to token " + token));
        user.setAccountEnabled(true);
        user.setAccountLocked(false);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        ;
    }

    private User createNewuser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return UserBuilder.createUser(firstName, lastName, email, role);
    }
}
