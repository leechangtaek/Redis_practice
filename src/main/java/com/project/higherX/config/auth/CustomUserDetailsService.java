package com.project.higherX.config.auth;

import com.project.higherX.exception.LoginFailureException;
import com.project.higherX.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        return userRepository.findAllByAccount(account)
                .map(this::createUserDetails)
                .orElseThrow(() -> new LoginFailureException());
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(com.project.higherX.domain.user.User user) {
        return User.builder()
                .username(user.getAccount())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
