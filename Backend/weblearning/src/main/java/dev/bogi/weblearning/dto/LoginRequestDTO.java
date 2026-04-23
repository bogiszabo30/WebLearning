package dev.bogi.weblearning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password
) {
}