package com.project.higherX.repository.todo;

import com.project.higherX.domain.todo.TodoState;
import com.project.higherX.dto.todo.TodoSimpleResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepositoryCustom {
    Slice<TodoSimpleResponseDto> findByState(TodoState state, Pageable pageable);
}
