package org.todo.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.todo.model.Todo;
import org.todo.dto.TodoDTO;
import org.todo.repository.TodoRepository;

@Log4j2
public enum TodoService {
        INSTANCE;

        private final TodoRepository repository = TodoRepository.INSTANCE;

        public List<TodoDTO> getList(Long memberId) {
                try {
                        return repository.getList(memberId).stream().map(TodoDTO::fromModel).collect(Collectors.toList());
                } catch (SQLException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                }
        }

        public void insert(TodoDTO dto) {
                try {
                        repository.insert(
                                Todo.builder()
                                        .content(dto.getContent())
                                        .dueDate(LocalDate.now())
                                        .done(false)
                                        .memberId(dto.getMemberId())
                                        .build()
                        );
                } catch (SQLException e) {
                        log.error(e);
                        throw new RuntimeException(e);
                }
        }

        public void update(TodoDTO updateTodo) {
                try {
                        Todo origin = repository.getById(updateTodo.getId());
                        repository.update(Todo.builder()
                                .id(origin.getId())
                                .content(updateTodo.getContent())
                                .done(updateTodo.isDone())
                                .dueDate(origin.getDueDate())
                                .build());
                } catch (SQLException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                }

        }

        public void delete(TodoDTO updateTodo) {
                try {
                        repository.deleteById(updateTodo.getId());
                } catch (SQLException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                }

        }
}
