package com.project.higherX.repository.user;

import com.project.higherX.domain.user.User;
import com.project.higherX.dto.user.UserInfoResponseDto;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findAllByAccount(String account);
    Optional<User> findByAccount(String account);
}
