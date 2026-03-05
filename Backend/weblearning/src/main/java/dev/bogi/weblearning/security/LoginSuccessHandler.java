package dev.bogi.weblearning.security;

import dev.bogi.weblearning.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Update last login
        userRepository.findByUsername(authentication.getName()).ifPresent(user -> {
            user.setLastLoginAt(ZonedDateTime.now());
            userRepository.save(user);
        });

        // Return JSON response
        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"success\"}");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
