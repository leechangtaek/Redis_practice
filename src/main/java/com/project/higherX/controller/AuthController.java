package com.project.higherX.controller;

import com.project.higherX.dto.sign.*;
import com.project.higherX.response.Response;
import com.project.higherX.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequestDto loginRequestDto) {
        return Response.success(authService.login(loginRequestDto));
    }

    @GetMapping("/token")
    public Response reissue(HttpServletRequest request) {
        return  Response.success(authService.reissue(request));
    }

    @PostMapping("/logout")
    public Response logout(HttpServletRequest request) {
        authService.logout(request);
        return Response.success();
    }
}
