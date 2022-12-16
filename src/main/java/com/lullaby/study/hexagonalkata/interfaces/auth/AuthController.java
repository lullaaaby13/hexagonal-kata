package com.lullaby.study.hexagonalkata.interfaces.auth;

import com.lullaby.study.hexagonalkata.security.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;


    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpRequest request) {
        System.out.println(request.getAccount());
        System.out.println(request.getPassword());
        System.out.println(request.getNickname());
        System.out.println("sign-up handler.");
    }

}
