package com.project.higherX.dto.todo;

import com.project.higherX.domain.todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoSimpleResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public TodoSimpleResponseDto(TodoSimpleResponseDto todo){
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.createdAt = todo.getCreatedAt();
    }
}
