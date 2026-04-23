package dev.bogi.weblearning.controller;

import dev.bogi.weblearning.dto.LoginRequestDTO;
import dev.bogi.weblearning.dto.RegistrationRequestDTO;
import dev.bogi.weblearning.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final CustomUserDetailsService userService;

    public LoginController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Map<String, String> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            userService.login(request);
            return Map.of("status", "success");
        } catch (RuntimeException e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }
}
