package com.lullaby.study.hexagonalkata.domain.model.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원 도메인")
class MemberTest {

    @DisplayName("정상적인 파라미터로 새로운 멤버 생성")
    @Test
    void create_member() {
        Member member = Member.join("normal", "1234", "lullaby");
        assertNotNull(member);
    }

    @DisplayName("계정 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_1() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("nor", "1234", "lullaby"));
    }

    @DisplayName("계정 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_2() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("nornornornornornornornornor", "1234", "lullaby"));
    }

    @DisplayName("비밀번호 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_3() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("normal", "123", "lullaby"));
    }

    @DisplayName("비밀번호 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_4() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("normal", "123456789012345678901234567890", "lullaby"));
    }

    @DisplayName("닉네임 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_5() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("normal", "1234", "lul"));
    }

    @DisplayName("닉네임 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_6() {
        assertThrows(MemberFieldInvalidException.class, () -> Member.join("normal", "1234", "lullabylullabylullabylullabylullaby"));
    }


}
