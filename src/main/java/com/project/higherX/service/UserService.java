package com.project.higherX.service;

import com.project.higherX.config.jwt.TokenProvider;
import com.project.higherX.domain.user.User;
import com.project.higherX.dto.sign.SignUpRequestDto;
import com.project.higherX.dto.user.UserInfoResponseDto;
import com.project.higherX.exception.TodoNotFoundException;
import com.project.higherX.exception.UserNotFoundException;
import com.project.higherX.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setAccount(signUpRequestDto.getAccount());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setNickname(signUpRequestDto.getNickname());
        user.setPhone(signUpRequestDto.getPhone());
        user.setCrn(signUpRequestDto.getCrn());
        user.setCreateAt(LocalDateTime.now());
        user.setRoles("USER");
        userRepository.save(user);
    }


    public UserInfoResponseDto userInfo(HttpServletRequest request) {
        User userInfo = selectUserInfo(request);

        return new UserInfoResponseDto(userInfo);
    }

    public boolean deleteUser(HttpServletRequest request) {
        User userInfo = selectUserInfo(request);

        if(userInfo == null){
            return false;
        }
        userInfo.setPhone(userInfo.getPhone().replaceAll("[0-9]","*"));
        userInfo.setCrn(userInfo.getCrn().replaceAll("[0-9]","*"));
        userInfo.setPassword("************");

        return true;
    }

    public User selectUserInfo(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization").substring(7);

        Authentication authentication = tokenProvider.getAuthentication(accessToken);

        User userInfo = userRepository.findByAccount(authentication.getName()).orElseThrow(UserNotFoundException::new);

        return userInfo;
    }
}
