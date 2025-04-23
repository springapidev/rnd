package com.example.attacks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank
        @Size(min = 4, max = 20)
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
        String username,
        @NotBlank
        @Size(min = 8)
        String password
) {
}
