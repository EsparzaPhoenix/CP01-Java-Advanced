package br.com.fiap.checkpoint01.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record  CadastroUserDto (

        @NotBlank
        @Size(max = 15, message = "Username deve ter no máximo 15 caracteres")
        String username,

        @NotBlank
        String password,

        @NotBlank
        String email) {
}