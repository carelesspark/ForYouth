package com.jhp.foryouth.find.service;

import com.jhp.foryouth.find.dto.FindPwDTO;
import com.jhp.foryouth.user.entity.UserAuth;
import com.jhp.foryouth.user.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ResetPwService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean updatePw(FindPwDTO dto) {
        Optional<UserAuth> value = userAuthRepository.findByUserId(dto.getUserId());
        if(value.isPresent()) {
            UserAuth userAuth = value.get();
            String encodePw = passwordEncoder.encode(dto.getUserPw());
            userAuth.setUserPw(encodePw);
            userAuthRepository.save(userAuth);
            return true;
        }
        return false;
    }
}
