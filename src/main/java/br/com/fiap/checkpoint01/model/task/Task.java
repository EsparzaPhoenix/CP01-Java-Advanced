package br.com.fiap.checkpoint01.model.task;

import br.com.fiap.checkpoint01.dto.task.CadastroTaskDto;
import br.com.fiap.checkpoint01.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="TB_JAVA_CP_TASK")
@Getter @Setter @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Column(name="id_task")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name="description", nullable = false, length = 500)
    private String description;

    @Column(name = "dt_expected_conclusion")
    private LocalTime dateExpectedConclusion;

    @Column(name = "st_task", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(CadastroTaskDto dto) {
        this.title = dto.title();
        this.description = dto.description();
        this.dateExpectedConclusion = dto.dateExpectedConclusion();
        this.status = Status.PENDENTE;
    }

    public void atualizarTask(CadastroTaskDto atualizar) {
        if(atualizar.title() != null)
            title = atualizar.title();
        if(atualizar.description() != null)
            description = atualizar.description();
        if(atualizar.dateExpectedConclusion() != null)
            dateExpectedConclusion = atualizar.dateExpectedConclusion();
    }
}
