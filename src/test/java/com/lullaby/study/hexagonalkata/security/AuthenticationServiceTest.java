package com.lullaby.study.hexagonalkata.security;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import com.lullaby.study.hexagonalkata.infrastructure.inmemory.MemberInmemoryRepository;
import com.lullaby.study.hexagonalkata.security.dto.SignInCommand;
import com.lullaby.study.hexagonalkata.security.dto.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("인증 서비스 (AuthenticationService)")
class AuthenticationServiceTest {

    MemberRepository memberRepository = new MemberInmemoryRepository();
    AuthenticationService authenticationService = new AuthenticationService(memberRepository, new JwtProvider());

    @DisplayName("정상적인 아이디와 비밀번호로 시도 시, 토큰을 반환 한다.")
    @Test
    void success_sign_in() {
        Member joinMember = Member.join("test", "1234", "test");
        memberRepository.save(joinMember);
        SignInCommand command = new SignInCommand(joinMember.getAccount(), joinMember.getPassword());
        Token token = authenticationService.signIn(command);
        assertNotNull(token);
        assertNotNull(token.accessToken());
        assertNotNull(token.refreshToken());
    }

    @DisplayName("비밀번호가 틀렸을 시, 에러가 발생 한다.")
    @Test
    void fail_sign_in() {
        Member joinMember = Member.join("test", "1234", "test");
        memberRepository.save(joinMember);
        assertThrows(AuthenticateFailException.class, () -> {
            SignInCommand command = new SignInCommand(joinMember.getAccount(), "wrong_password");
            authenticationService.signIn(command);
        });
    }

}
