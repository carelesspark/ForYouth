package com.jhp.foryouth.join.controller;

import com.jhp.foryouth.join.repository.CheckEmailRepository;
import com.jhp.foryouth.join.repository.CheckIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CheckUserInfoController {

    private final CheckIdRepository checkIdRepository;
    private final CheckEmailRepository checkEmailRepository;

    @GetMapping("/check-userId")
    public Map<String, Boolean> checkUserId(@RequestParam String userId) {
        boolean exists = checkIdRepository.existsByUserId(userId);
        return Map.of("exists", exists);
    }

    @GetMapping("/check-userEmail")
    public Map<String, Boolean> checkUserEmail(@RequestParam String userEmail) {
        boolean exists = checkEmailRepository.existsByUserEmail(userEmail);
        return Map.of("exists", exists);
    }
}
