package dev.bogi.weblearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
public class HealthController {

    @PostMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "UP");
        return map;
    }
}
