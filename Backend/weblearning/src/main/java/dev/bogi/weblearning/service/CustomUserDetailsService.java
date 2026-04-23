package dev.bogi.weblearning.service;

import dev.bogi.weblearning.dto.AuthResponseDTO;
import dev.bogi.weblearning.dto.LoginRequestDTO;
import dev.bogi.weblearning.dto.RegistrationRequestDTO;
import dev.bogi.weblearning.model.user.Role;
import dev.bogi.weblearning.model.user.User;
import dev.bogi.weblearning.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(login)
                .or(() -> userRepository.findByEmail(login))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRoles().stream().map(Role::name).toArray(String[]::new))
                .build();
    }

    // helper method for registration
    public User register(RegistrationRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(requestDTO.username());
        user.setEmail(requestDTO.email());
        user.setPasswordHash(passwordEncoder.encode(requestDTO.password()));
        user.getRoles().add(Role.USER);
        return userRepository.save(user);
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        // 1. Authenticate credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.username(),
                        requestDTO.password()
                )
        );

        // 2. Generate JWT token
        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthResponseDTO(token);

    }
}
