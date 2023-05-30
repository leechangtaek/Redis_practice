package com.project.higherX.dto.sign;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;


}
