package com.jhp.foryouth.join.controller;

import com.jhp.foryouth.join.repository.CheckIdRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CheckIdController {

    private final CheckIdRepository checkIdRepository;

    public CheckIdController(CheckIdRepository checkIdRepository) {
        this.checkIdRepository = checkIdRepository;
    }

    @GetMapping("/check-userid")
    public Map<String, Boolean> checkUserId(@RequestParam String userId) {
        boolean exists = checkIdRepository.existsByUserId(userId);
        return Map.of("exists", exists);
    }
}
