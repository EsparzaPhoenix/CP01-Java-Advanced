package br.com.fiap.checkpoint01.controller;

import br.com.fiap.checkpoint01.dto.task.CadastroTaskDto;
import br.com.fiap.checkpoint01.dto.task.DetalhesTaskDto;
import br.com.fiap.checkpoint01.model.task.Task;
import br.com.fiap.checkpoint01.repository.TaskRepository;
import br.com.fiap.checkpoint01.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTaskDto> cadastrar(@RequestBody @Valid CadastroTaskDto dto,
                                                     UriComponentsBuilder uri,
                                                     Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body(null);
        }

        var task = new Task(dto);
        taskRepository.save(task);
        var url = uri.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTaskDto(task));
    }

    @GetMapping
    public ResponseEntity<List<DetalhesTaskDto>> listarTasks(Pageable pageable,
                                                             Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body(null);
        }

        var lista = taskRepository.findAll(pageable)
                .stream().map(DetalhesTaskDto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesTaskDto> listarTask(@PathVariable("id") Long id,
                                                      Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body(null);
        }

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        return ResponseEntity.ok(new DetalhesTaskDto(task));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesTaskDto> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody @Valid CadastroTaskDto dto,
                                                     Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body(null);
        }

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        task.atualizarTask(dto);
        return ResponseEntity.ok(new DetalhesTaskDto(task));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(403).body(null);
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
