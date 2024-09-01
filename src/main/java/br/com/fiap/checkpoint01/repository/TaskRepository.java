package br.com.fiap.checkpoint01.repository;

import br.com.fiap.checkpoint01.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
