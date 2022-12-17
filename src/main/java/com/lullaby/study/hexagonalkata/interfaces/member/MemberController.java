package com.lullaby.study.hexagonalkata.interfaces.member;

import com.lullaby.study.hexagonalkata.application.member.MemberProfileModel;
import com.lullaby.study.hexagonalkata.application.member.MemberService;
import com.lullaby.study.hexagonalkata.security.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal AuthenticatedUser user) {
        MemberProfileModel profile = memberService.getProfile(user.getUserId());
        return ResponseEntity.ok(profile);
    }

}
