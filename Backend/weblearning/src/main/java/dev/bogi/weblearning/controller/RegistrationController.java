package dev.bogi.weblearning.controller;

import dev.bogi.weblearning.dto.RegistrationRequestDTO;
import dev.bogi.weblearning.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final AuthService authService;

    public RegistrationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegistrationRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }
}
