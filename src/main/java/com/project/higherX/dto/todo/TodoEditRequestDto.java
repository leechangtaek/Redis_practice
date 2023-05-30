package com.project.higherX.dto.todo;

import com.project.higherX.domain.todo.TodoState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoEditRequestDto {
    private Long id;
    private String name;
    private String description;

    @NotNull(message = "유효하지 않은 상태가 입력되었습니다.")
    private TodoState state;
}
