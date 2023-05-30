package com.project.higherX.domain.todo;

import com.project.higherX.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "유효하지 않은 상태가 입력되었습니다.")
    private TodoState state;

    private LocalDateTime createdAt;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
