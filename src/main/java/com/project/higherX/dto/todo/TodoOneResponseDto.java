package com.project.higherX.dto.todo;

import com.project.higherX.domain.todo.Todo;
import com.project.higherX.domain.todo.TodoState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TodoOneResponseDto {
    private Long id;
    private String name;
    private String description;
    private TodoState state;

    public TodoOneResponseDto(Todo todo){
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.state = todo.getState();
    }
}
