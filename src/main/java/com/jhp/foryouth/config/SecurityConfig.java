package com.jhp.foryouth.config;

import com.jhp.foryouth.join.service.impl.KakaoJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KakaoJoinService kakaoJoinService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login/**", "/join", "/join/**", "/find/**", "/css/**", "/js/**", "/images/**", "/check-userid", "/fonts/**", "/favicon.ico", "/error", "webjars/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login/loginMain")
                        .loginProcessingUrl("/login/loginProcess")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login/loginMain")
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(kakaoJoinService)));
        return http.build();
    }
}
