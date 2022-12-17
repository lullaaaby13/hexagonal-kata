package com.lullaby.study.hexagonalkata.application.member;

import com.lullaby.study.hexagonalkata.domain.model.member.MemberFieldInvalidException;
import com.lullaby.study.hexagonalkata.infrastructure.inmemory.MemberInmemoryRepository;
import com.lullaby.study.hexagonalkata.infrastructure.inmemory.PointHistoryInmemoryRepository;
import com.lullaby.study.hexagonalkata.infrastructure.jpa.MemberRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원 서비스 테스트")
class MemberServiceImplTest {

    MemberServiceImpl memberServiceImpl = new MemberServiceImpl(new MemberInmemoryRepository(), new PointHistoryInmemoryRepository());

    @DisplayName("회원 가입 성공")
    @Test
    void success_join() {
        // Given
        JoinCommand joinCommand = new JoinCommand("normal", "1234", "lullaby");
        // when
        memberServiceImpl.join(joinCommand);
    }

    @DisplayName("계정 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_1() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("nor", "1234", "lullaby");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

    @DisplayName("계정 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_2() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("nornornornornornornornornor", "1234", "lullaby");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

    @DisplayName("비밀번호 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_3() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("normal", "123", "lullaby");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

    @DisplayName("비밀번호 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_4() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("normal", "123456789012345678901234567890", "lullaby");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

    @DisplayName("닉네임 길이가 4 미만일 시 에러")
    @Test
    void create_member_fail_5() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("normal", "1234", "lul");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

    @DisplayName("닉네임 길이가 20 초과 시 에러")
    @Test
    void create_member_fail_6() {
        assertThrows(MemberFieldInvalidException.class, () -> {
            // Given
            JoinCommand joinCommand = new JoinCommand("normal", "1234", "lullabylullabylullabylullabylullaby");
            // when
            memberServiceImpl.join(joinCommand);
        });
    }

}
