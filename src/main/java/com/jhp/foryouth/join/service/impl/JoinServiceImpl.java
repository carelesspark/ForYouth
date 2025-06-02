package com.jhp.foryouth.join.service.impl;

import com.jhp.foryouth.join.repository.CheckIdRepository;
import com.jhp.foryouth.join.repository.JoinUserAuthRepository;
import com.jhp.foryouth.join.repository.JoinUserRepository;
import com.jhp.foryouth.join.service.JoinService;
import com.jhp.foryouth.user.domain.User;
import com.jhp.foryouth.user.domain.UserAuth;
import com.jhp.foryouth.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinUserRepository joinUserRepository;
    private final JoinUserAuthRepository joinUserAuthRepository;
    private final CheckIdRepository checkIdRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void join(UserDTO userDTO) {
        if(checkIdRepository.existsByUserId(userDTO.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User userEntity = dtoToUserEntity(userDTO);
        joinUserRepository.save(userEntity);

        String pw = userDTO.getUserPw();
        String encodePw = passwordEncoder.encode(pw);
        userDTO.setUserPw(encodePw);

        UserAuth userAuthEntity = dtoToUserAuthEntity(userEntity, userDTO);
        joinUserAuthRepository.save(userAuthEntity);
    }
}
