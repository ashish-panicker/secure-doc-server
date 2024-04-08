package com.easyskillup.sdoc.resource;

import com.easyskillup.sdoc.domain.dto.UserRequest;
import com.easyskillup.sdoc.domain.dto.Response;
import com.easyskillup.sdoc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.easyskillup.sdoc.utils.RequestUtils.*;
import static java.util.Collections.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserRequest request, HttpServletRequest servletRequest){
        userService.createUser(request.firstName(), request.lastName(), request.email(), request.password());
        return ResponseEntity.created(getUri()).body(getResponse(servletRequest, emptyMap(),"Account created.", CREATED));
    }

    private URI getUri(){
        return URI.create("");
    }
}
