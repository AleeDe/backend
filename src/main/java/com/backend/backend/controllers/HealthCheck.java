package com.backend.backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Application is running fine";
    }
    
}
