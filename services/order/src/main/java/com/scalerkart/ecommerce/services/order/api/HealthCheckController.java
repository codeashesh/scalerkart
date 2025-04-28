package com.scalerkart.ecommerce.services.order.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<String> checkHealth() {
        log.info("Health Check Request Received");
        return ResponseEntity.ok("Success");
    }
}
