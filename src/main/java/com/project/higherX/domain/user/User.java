package com.project.higherX.domain.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String id;

    private String account;
    private String password;
    private String nickname;
    private String phone;
    private String crn;
    private LocalDateTime createAt;
    private String roles;



}
