package com.jhp.foryouth.find.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthenticationCodeService {

    private static final String TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();


    public static String createCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for(int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(TEXT.length());
            code.append(TEXT.charAt(index));
        }
        return code.toString();
    }

}
