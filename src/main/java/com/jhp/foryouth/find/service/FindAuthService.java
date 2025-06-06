package com.jhp.foryouth.find.service;

import com.jhp.foryouth.user.domain.User;
import com.jhp.foryouth.user.domain.UserAuth;
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
    private final EmailUserIdService emailUserIdService;

    public boolean findUserIdByNameAndEmail(String name, String email) throws MessagingException {
        Optional<User> user = userRepository.findByUserNameAndUserEmail(name, email);


        if(user.isPresent()) {
            Long userNum = user.get().getNum();
            Optional<UserAuth> userAuth = userAuthRepository.findByUserNum(userNum);
            if(userAuth.isPresent()) {
                String userId = userAuth.get().getUserId();
                emailUserIdService.emailContent(userId, email);
                return true;
            }
        }
        return false;
    }
}


