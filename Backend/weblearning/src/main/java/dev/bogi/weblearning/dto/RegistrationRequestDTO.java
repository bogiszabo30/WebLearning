package dev.bogi.weblearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDTO(
        @NotBlank String username,
        @Email String email,
        @NotBlank @Size(min = 6) String password
) {
}