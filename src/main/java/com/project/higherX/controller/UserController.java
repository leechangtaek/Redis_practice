package com.project.higherX.controller;

import com.project.higherX.dto.sign.SignUpRequestDto;
import com.project.higherX.dto.sign.SignUpResponseDto;
import com.project.higherX.dto.user.UserInfoResponseDto;
import com.project.higherX.exception.AccountAlreadyExistsException;
import com.project.higherX.exception.CrnInvalidException;
import com.project.higherX.exception.NicknameAlreadyExistsException;
import com.project.higherX.response.Response;
import com.project.higherX.service.AuthService;
import com.project.higherX.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/user")
    public Response register(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        if(accountExists(signUpRequestDto.getAccount())){
            throw new AccountAlreadyExistsException();
        }
        if (nicknameExists(signUpRequestDto.getNickname())) {
            throw new NicknameAlreadyExistsException();
        }
        if (crnValidChk(signUpRequestDto.getCrn())) {
            throw new CrnInvalidException();
        }

        userService.signUp(signUpRequestDto);
        return Response.success();
    }

    @GetMapping("/user")
    public Response findByUser(HttpServletRequest request){
        return Response.success(userService.userInfo(request));
    }
    @DeleteMapping("/user")
    public Response deleteUser(HttpServletRequest request){
        authService.logout(request);

        return  Response.success(userService.deleteUser(request));
    }


    private boolean accountExists(String account) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/verify/account?account="+account;
        ResponseEntity<SignUpResponseDto> response = restTemplate.getForEntity(url, SignUpResponseDto.class);
        return response.getBody().isVerify();
    }
    private boolean nicknameExists(String nickname) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/verify/nickname?nickname="+nickname;
        ResponseEntity<SignUpResponseDto> response = restTemplate.getForEntity(url,  SignUpResponseDto.class);
        return response.getBody().isVerify();
    }
    private boolean crnValidChk(String crn) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/verify/crn?crn="+crn;
        ResponseEntity<SignUpResponseDto> response = restTemplate.getForEntity(url,  SignUpResponseDto.class);
        return response.getBody().isVerify();
    }
}
