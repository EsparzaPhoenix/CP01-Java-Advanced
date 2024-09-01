package br.com.fiap.checkpoint01.dto.task;

import br.com.fiap.checkpoint01.model.enums.Status;
import br.com.fiap.checkpoint01.model.task.Task;

import java.time.LocalTime;

public record DetalhesTaskDto (
        long id,
        String title,
        String description,
        LocalTime dateExpectedConclusion,
        Status status
){
    public DetalhesTaskDto(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateExpectedConclusion(),
                task.getStatus()
        );
    }
}
