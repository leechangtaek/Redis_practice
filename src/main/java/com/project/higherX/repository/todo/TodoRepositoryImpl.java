package com.project.higherX.repository.todo;

import com.project.higherX.domain.todo.QTodo;
import com.project.higherX.domain.todo.Todo;
import com.project.higherX.domain.todo.TodoState;
import com.project.higherX.dto.todo.TodoListResponseDto;
import com.project.higherX.dto.todo.TodoSimpleResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{
    private final EntityManager em;

    @Override
    public Slice<TodoSimpleResponseDto> findByState(TodoState state, Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QTodo todo = QTodo.todo;
        List<TodoSimpleResponseDto> todoList = query
                .select(Projections.constructor(TodoSimpleResponseDto.class,
                        todo.id,
                        todo.name,
                        todo.description,
                        todo.createdAt))
                .from(todo)
                .where(stateEq(state))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // limit보다 데이터를 1개 더 들고와서, 해당 데이터가 있다면 hasNext 변수에 true를 넣어 알림
                .fetch();


        List<TodoSimpleResponseDto> content = new ArrayList<>();
        for (TodoSimpleResponseDto todoOne: todoList) {
            content.add(new TodoSimpleResponseDto(todoOne));
        }

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }

    //동적 쿼리를 위한 BooleanExpression
    private BooleanExpression stateEq(TodoState state) {
        if(ObjectUtils.isEmpty(state)||state.toString().equals("ALL") ){
            return null;
        }
        return QTodo.todo.state.eq(state);

    }
}
