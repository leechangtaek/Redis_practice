package com.project.higherX.controller;


import com.project.higherX.domain.todo.TodoState;
import com.project.higherX.dto.todo.*;
import com.project.higherX.response.Response;
import com.project.higherX.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todo")
    public Response findAllTodos(@RequestParam(required = false) TodoState state,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        pageable = PageRequest.of(0,size);

        return Response.success(todoService.findAllTodos(state,pageable));
    }

    @PostMapping("/todo")
    public Response createTodo(HttpServletRequest request, @RequestBody TodoCreateRequestDto todoCreateRequestDto){

        todoService.createTodo(request, todoCreateRequestDto);
        return Response.success(true);
    }

    @PutMapping("/todo")
    public Response editTodo(@Valid @RequestBody TodoEditRequestDto todoEditRequestDto){

        todoService.editTodo(todoEditRequestDto);
        return Response.success();
    }

    @GetMapping("/todo/{todoId}")
    public Response simpleTodo(@PathVariable("todoId") Long todoId){

        return Response.success(todoService.findById(todoId));
    }

    @DeleteMapping("/todo")
    public Response deleteTodo(HttpServletRequest request, @RequestParam Long id){
        todoService.deleteTodo(request,id);
        return Response.success();
    }


}
