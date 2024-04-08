package com.easyskillup.sdoc.resource;

import com.easyskillup.sdoc.domain.dto.UserRequest;
import com.easyskillup.sdoc.domain.dto.Response;
import com.easyskillup.sdoc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.easyskillup.sdoc.utils.RequestUtils.*;
import static java.util.Collections.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserRequest request, HttpServletRequest servletRequest){
        userService.createUser(request.firstName(), request.lastName(), request.email(), request.password());
        return ResponseEntity.created(getUri()).body(getResponse(servletRequest, emptyMap(),"Account created.", CREATED));
    }

    @GetMapping("/verify/account")
    public ResponseEntity<Response> verifyAccount(@RequestParam String token, HttpServletRequest request){
        userService.verifyAccountKey(token);
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Account verified.", OK));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest user){
        var unauth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(user.email(), user.password()));
        return ResponseEntity.ok().body(unauth);
    }

    private URI getUri(){
        return URI.create("");
    }
}
