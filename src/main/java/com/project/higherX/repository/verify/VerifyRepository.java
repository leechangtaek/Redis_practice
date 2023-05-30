package com.project.higherX.repository.verify;

import com.project.higherX.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyRepository extends JpaRepository<User,Long> {

    boolean existsByAccount(String account);

    boolean existsByNickname(String nickname);
}
