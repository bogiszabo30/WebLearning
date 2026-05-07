package dev.bogi.weblearning.service;

import dev.bogi.weblearning.dto.AuthResponseDTO;
import dev.bogi.weblearning.dto.LoginRequestDTO;
import dev.bogi.weblearning.dto.RegistrationRequestDTO;
import dev.bogi.weblearning.model.user.Role;
import dev.bogi.weblearning.model.user.User;
import dev.bogi.weblearning.repository.UserRepository;
import dev.bogi.weblearning.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                       UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Transactional
    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.username(),
                        requestDTO.password()));
        String token = jwtTokenProvider.generateToken(authentication);
        userRepository.findByUsername(authentication.getName()).ifPresent(user -> {
            user.setLastLoginAt(ZonedDateTime.now());
            userRepository.save(user);
        });
        return new AuthResponseDTO(token, jwtTokenProvider.getJwtExpiration(), authentication.getName());
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

    @Transactional
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
}
