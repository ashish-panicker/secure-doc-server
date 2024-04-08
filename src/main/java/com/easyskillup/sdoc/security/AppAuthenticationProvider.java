package com.easyskillup.sdoc.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = (UsernamePasswordAuthenticationToken) authentication;
        if(authentication.getCredentials() == null){
            log.error("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("Failed to authenticate since no credentials provided");
        }
        String presentedPassword = authentication.getCredentials().toString();
        var appUser = userDetailsService.loadUserByUsername((String) user.getPrincipal());
        var encodedPassword = passwordEncoder.encode(presentedPassword);
        if(presentedPassword.equals(appUser.getPassword())){
            return UsernamePasswordAuthenticationToken.authenticated(appUser, "[Protected]",appUser.getAuthorities());
        }
        log.error("Failed to authenticate as bad credentials were provided");
        throw new BadCredentialsException("Unable to login");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
