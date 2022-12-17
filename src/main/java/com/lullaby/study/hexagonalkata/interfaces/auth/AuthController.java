package com.lullaby.study.hexagonalkata.interfaces.auth;

import com.lullaby.study.hexagonalkata.application.member.AlreadyExistMemberException;
import com.lullaby.study.hexagonalkata.application.member.JoinCommand;
import com.lullaby.study.hexagonalkata.application.member.MemberService;
import com.lullaby.study.hexagonalkata.security.AuthenticateFailException;
import com.lullaby.study.hexagonalkata.security.AuthenticationService;
import com.lullaby.study.hexagonalkata.security.dto.SignInCommand;
import com.lullaby.study.hexagonalkata.security.dto.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;
    private final MemberService memberService;

    public AuthController(AuthenticationService authenticationService, MemberService memberService) {
        this.authenticationService = authenticationService;
        this.memberService = memberService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@Validated @RequestBody SignUpRequest request) {
        try {
            JoinCommand joinCommand = new JoinCommand(request.getAccount(), request.getPassword(), request.getNickname());
            memberService.join(joinCommand);
        } catch (AlreadyExistMemberException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@Validated @RequestBody SignInRequest request) {
        SignInCommand command = new SignInCommand(request.getAccount(), request.getPassword());
        try {
            Token token = authenticationService.signIn(command);
            return ResponseEntity.ok()
                    .body(token);
        } catch (AuthenticateFailException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }
}
