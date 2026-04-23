package dev.bogi.weblearning.dto;

public record AuthResponseDTO(
        String token,
        String tokenType,
        long expiresIn,
        String username
) {
    // Compact constructor to set default token type
    public AuthResponseDTO(String token, long expiresIn, String username) {
        this(token, "Bearer", expiresIn, username);
    }
}