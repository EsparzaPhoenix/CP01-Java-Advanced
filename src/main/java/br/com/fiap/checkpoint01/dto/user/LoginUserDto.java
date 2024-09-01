package br.com.fiap.checkpoint01.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDto (

        @NotBlank
        String login,
        @NotBlank
        String password

    ) {
}
