package com.jhp.foryouth.config;

import com.jhp.foryouth.join.service.impl.KakaoJoinService;
import com.jhp.foryouth.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    private final KakaoJoinService kakaoJoinService;
    private final LoginService loginService;
    private final PasswordConfig passwordConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login/**", "/join", "/join/**", "/find/**", "/css/**", "/js/**", "/images/**", "/check-userid", "/fonts/**", "/favicon.ico", "/error", "webjars/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login/loginMain")
                        .loginProcessingUrl("/login/loginProcess")
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login/loginMain?error=true")
                        .permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login/loginMain")
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(kakaoJoinService)));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginService);
        authProvider.setPasswordEncoder(passwordConfig.passwordEncoder());

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(authProvider);

        return auth.build();
    }
}