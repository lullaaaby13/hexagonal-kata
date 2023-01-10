package com.lullaby.study.hexagonalkata.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("health-check")
    public HealCheckResponse healthCheck() {
        return new HealCheckResponse("Hi", "success");
    }

    public static record HealCheckResponse(
            String message,
            String type
    ) {

    }

}

