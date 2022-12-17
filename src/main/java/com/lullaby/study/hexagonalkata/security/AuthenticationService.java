package com.lullaby.study.hexagonalkata.security;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberNotFoundException;
import com.lullaby.study.hexagonalkata.domain.model.member.MemberRepository;
import com.lullaby.study.hexagonalkata.security.dto.SignInCommand;
import com.lullaby.study.hexagonalkata.security.dto.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public AuthenticationService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public Token signIn(SignInCommand command) {
        Member member = memberRepository.findByAccount(command.account())
                .orElseThrow(AuthenticateFailException::new);

        if (!StringUtils.equals(member.getPassword(), command.password())) {
            throw new AuthenticateFailException();
        }

        return new Token(
                jwtProvider.accessToken(member.getId())
                , jwtProvider.refreshToken(member.getId())
        );
    }


}
