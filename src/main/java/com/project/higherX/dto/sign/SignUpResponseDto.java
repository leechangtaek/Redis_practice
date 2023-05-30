package com.project.higherX.dto.sign;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignUpResponseDto {
    private boolean verify;

    public SignUpResponseDto(boolean verify) {
        this.verify = verify;
    }
}
