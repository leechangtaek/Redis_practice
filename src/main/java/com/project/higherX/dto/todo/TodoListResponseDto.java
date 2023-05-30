package com.project.higherX.dto.todo;

import com.project.higherX.domain.todo.Todo;
import com.project.higherX.domain.todo.TodoState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoListResponseDto {

    private int totalElements;
    private int totalPages;
    private boolean hasNext;
    private List<TodoSimpleResponseDto> todoList;


    public TodoListResponseDto(List<TodoSimpleResponseDto> todoList, int totalElements, int totalPages, boolean hasNext) {
        this.todoList = todoList;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
    }
}
