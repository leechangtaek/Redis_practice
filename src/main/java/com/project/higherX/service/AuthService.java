package com.project.higherX.service;

import com.project.higherX.config.jwt.TokenProvider;
import com.project.higherX.config.redis.RedisUtil;
import com.project.higherX.domain.user.User;
import com.project.higherX.dto.sign.LoginRequestDto;
import com.project.higherX.dto.sign.TokenDto;
import com.project.higherX.dto.sign.TokenResponseDto;
import com.project.higherX.exception.LoginFailureException;
import com.project.higherX.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisUtil redisUtils;

    @Transactional
    public TokenResponseDto login(LoginRequestDto loginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getAccount(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenDto tokenDto = tokenProvider.generateToken(authentication);
        redisTemplate.opsForValue()
                .set("RT:" + loginRequestDto.getAccount(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpiresIn(), TimeUnit.MILLISECONDS);

        return new TokenResponseDto(tokenDto.getAccessToken());
    }
    @Transactional
    public void logout(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        TokenDto tokenDto = tokenProvider.generateToken(authentication);

        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }
        redisUtils.setBlackList(accessToken, "accessToken", 1800);
        redisUtils.setBlackList(tokenDto.getRefreshToken(), "refreshToken", 60400);
    }
    @Transactional
    public TokenResponseDto reissue(HttpServletRequest request) {

        String accessToken = request.getHeader("Authorization").substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        TokenDto tokenDto = tokenProvider.generateToken(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpiresIn(), TimeUnit.MILLISECONDS);

        return new TokenResponseDto(tokenDto.getAccessToken());
    }

}
