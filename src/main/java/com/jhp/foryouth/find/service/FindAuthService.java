package com.jhp.foryouth.find.service;

import com.jhp.foryouth.user.entity.User;
import com.jhp.foryouth.user.entity.UserAuth;
import com.jhp.foryouth.user.repository.UserAuthRepository;
import com.jhp.foryouth.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class FindAuthService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final EmailUserService emailUserService;
    private final RedisAuthService redisAuthService;

    public boolean findUserIdByNameAndEmail(String userName, String userEmail) throws MessagingException {
        Optional<User> user = userRepository.findByUserNameAndUserEmail(userName, userEmail);


        if(user.isPresent()) {
            Long userNum = user.get().getNum();
            Optional<UserAuth> userAuth = userAuthRepository.findByUserNum(userNum);
            if(userAuth.isPresent()) {
                String userId = userAuth.get().getUserId();
                emailUserService.sendEmailAboutUserId(userId, userEmail);
                return true;
            }
        }
        return false;
    }

    public boolean findUserByNameAndIdAndEmail(String userName, String userId, String userEmail) throws MessagingException {
        Optional<User> user = userRepository.findByUserNameAndUserEmail(userName, userEmail);

        if(user.isPresent()) {
            Long userNum = user.get().getNum();
            Optional<UserAuth> userAuth = userAuthRepository.findByUserNum(userNum);
            if(userAuth.isPresent()) {
                String code = AuthenticationCodeService.createCode();
                String redisKey = "find_pw : " + userId;
                redisAuthService.setDataExpire(redisKey, code, 60*5L);
                emailUserService.sendEmailAboutCode(code, userEmail);
                return true;
            }
        }
        return false;
    }

    public boolean checkCodeIsRight(String userId, String inputCode) {
        String redisKey = "find_pw : " + userId;
        String savedCode = redisAuthService.getData(redisKey);
        log.info("입력한 코드 값 : {}, 저장된 코드 값 : {}", inputCode, savedCode);

        if(savedCode != null && savedCode.equals(inputCode)) {
            return true;
        } else {
            return false;
        }
    }
}


