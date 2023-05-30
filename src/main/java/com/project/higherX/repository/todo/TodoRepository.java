package com.project.higherX.repository.todo;

import com.project.higherX.domain.todo.Todo;
import com.project.higherX.domain.todo.TodoState;
import com.project.higherX.dto.todo.TodoListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long>,TodoRepositoryCustom {

}
