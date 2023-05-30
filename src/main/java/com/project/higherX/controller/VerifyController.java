package com.project.higherX.controller;

import com.project.higherX.dto.sign.SignUpResponseDto;
import com.project.higherX.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VerifyController {

    private final VerifyService verifyService;

    @GetMapping("/verify/account")
    public SignUpResponseDto accountExists(@RequestParam String account) {
        boolean exists = verifyService.accountExists(account);

        SignUpResponseDto response = new SignUpResponseDto();
        if (exists) {
            response.setVerify(false);
        } else {
            response.setVerify(true);
        }

        return response;
    }
    @GetMapping("/verify/nickname")
    public SignUpResponseDto nicknameExists(@RequestParam String nickname) {

        boolean exists = verifyService.nicknameExists(nickname);

        SignUpResponseDto response = new SignUpResponseDto();
        if (exists) {
            response.setVerify(false);
        } else {
            response.setVerify(true);
        }

        return response;
    }
    @GetMapping("/verify/crn")
    public SignUpResponseDto crnValidCHk(@RequestParam String crn) {

        boolean exists = verifyService.crnValidCHk(crn);

        SignUpResponseDto response = new SignUpResponseDto();
        if (exists) {
            response.setVerify(false);
        } else {
            response.setVerify(true);
        }

        return response;
    }
}
