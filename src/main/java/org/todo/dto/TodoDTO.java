package org.todo.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.todo.model.Todo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
        private Long id;
        private String content;
        private boolean done;
        private LocalDate dueDate;
        private Long memberId;

        public static TodoDTO fromModel(Todo todo) {
                return TodoDTO.builder()
                        .id(todo.getId())
                        .content(todo.getContent())
                        .dueDate(todo.getDueDate())
                        .done(todo.isDone())
                        .memberId(todo.getMemberId())
                        .build();
        }
}
