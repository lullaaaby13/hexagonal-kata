package com.lullaby.study.hexagonalkata;

import com.lullaby.study.hexagonalkata.utils.DatabaseCleanUp;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    DatabaseCleanUp databaseCleanUp;

    boolean isInitialized = false;

    @BeforeEach
    public void beforeEach() {

        if (!isInitialized) {
            databaseCleanUp.afterPropertiesSet();
            isInitialized = true;
        }

        databaseCleanUp.execute();
    }

}
