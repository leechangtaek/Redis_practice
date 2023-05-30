package com.project.higherX.dto.sign;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenResponseDto {

    private String accessToken;

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
