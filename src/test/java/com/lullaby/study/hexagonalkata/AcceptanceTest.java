package com.lullaby.study.hexagonalkata;

import com.lullaby.study.hexagonalkata.infrastructure.inmemory.MemberInmemoryRepository;
import com.lullaby.study.hexagonalkata.utils.DatabaseCleanUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    MemberInmemoryRepository memberInmemoryRepository;
    @LocalServerPort
    int port;
    @Autowired
    DatabaseCleanUp databaseCleanUp;

    @BeforeEach
    public void beforeEach() {

        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanUp.afterPropertiesSet();
        }

        databaseCleanUp.execute();
        // TODO 테스트 끝나면 지워야 됨
        memberInmemoryRepository.clean();
    }

}
