package com.project.higherX.service;

import com.project.higherX.repository.verify.VerifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {
    private final VerifyRepository verifyRepository;

    private final int[] CERTIFY_NUM = {1, 3, 7, 1, 3, 7, 1, 3, 5, 1};

    public boolean accountExists(String account) {
        if (verifyRepository.existsByAccount(account)) {
            return false;
        }
        return true;
    }

    public boolean nicknameExists(String nickname) {
        if (verifyRepository.existsByNickname(nickname)) {
            return false;
        }
        return true;
    } 

    public boolean crnValidCHk(String crn) {
        if(crn.replaceAll("[^0-9]","").length() != 10){
            return false;
        }

        int sum = 0;
        int j = -1;
        for (int i = 0; i < 9; i++) {
            j = Character.getNumericValue(crn.charAt(i));
            sum += j * CERTIFY_NUM[i];
        }
        sum += (int) (Character.getNumericValue(crn.charAt(8)) * 5 /10);

        if(1 != (10 - (sum % 10))){
            return false;
        }

        return true;
    }
}
