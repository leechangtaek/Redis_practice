package com.project.higherX.dto.user;

import com.project.higherX.domain.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserInfoResponseDto {

    private String nickname;
    private String phone;
    private String crn;
    private LocalDateTime createAt;

    public UserInfoResponseDto(User user){
        nickname = user.getNickname();
        phone = user.getPhone();
        crn = user.getCrn();
        createAt = user.getCreateAt();
    }
}
