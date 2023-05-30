package com.project.higherX.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String account;
    private String password;
    private String nickname;
    private String phone;
    private String crn;
}
