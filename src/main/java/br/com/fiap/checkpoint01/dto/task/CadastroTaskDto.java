package br.com.fiap.checkpoint01.dto.task;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record CadastroTaskDto (

        @NotBlank
        @Size(max = 100, message = "titulo deve ter no maximo 100 caracteres")
        String title,

        @NotBlank
        @Size(max = 500, message = "Descrição deve ter no maximo 500 caracteres")
        String description,

        @NotBlank
        @Future(message = "O horario selecionado não pode ser uma data que ja passou")
        LocalTime dateExpectedConclusion
) {
}