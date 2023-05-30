package com.project.higherX.service;

import com.project.higherX.domain.todo.Todo;
import com.project.higherX.domain.todo.TodoState;
import com.project.higherX.domain.user.User;
import com.project.higherX.dto.todo.*;
import com.project.higherX.exception.TodoNotFoundException;
import com.project.higherX.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    @Transactional
    public void createTodo(HttpServletRequest request, TodoCreateRequestDto todoCreateRequestDto) {

        User userInfo = userService.selectUserInfo(request);

        Todo todo = new Todo();
        todo.setName(todoCreateRequestDto.getName());
        todo.setDescription(todoCreateRequestDto.getDescription());
        todo.setCreatedAt(LocalDateTime.now());
        todo.setState(TodoState.INCOMPLETE);
        todo.setUser(userInfo);

        todoRepository.save(todo);

    }
    @Transactional
    public void editTodo(TodoEditRequestDto todoEditRequestDto) {

        Todo todo = todoRepository.findById(todoEditRequestDto.getId()).orElseThrow(TodoNotFoundException::new);

        todo.setName(todoEditRequestDto.getName());
        todo.setDescription(todoEditRequestDto.getDescription());
        todo.setState(todoEditRequestDto.getState());
    }

    public TodoOneResponseDto findById(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);

        return new TodoOneResponseDto(todo);
    }

    @Transactional
    public void deleteTodo(HttpServletRequest request, Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFoundException::new);
        User userInfo = userService.selectUserInfo(request);
        
        if(userInfo.getId() == todo.getUser().getId()){
            todoRepository.deleteById(todoId);
        }

    }

    public TodoListResponseDto findAllTodos(TodoState state, Pageable pageable) {
        Slice<TodoSimpleResponseDto> todos = todoRepository.findByState(state,pageable);

        return new TodoListResponseDto(todos.getContent(),todos.getNumberOfElements(),todos.getSize(),todos.hasNext());
    }
}
