package com.jhp.foryouth.login.config;

import com.jhp.foryouth.join.service.impl.OAuthJoinService;
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
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    private final OAuthJoinService OAuthJoinService;
    private final LoginService loginService;
    private final PasswordConfig passwordConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login/**", "/oauth2/**", "/join", "/join/**", "/find/**", "/css/**", "/js/**", "/images/**", "/check-userid", "/auth/send-code", "/auth/check-code", "/fonts/**", "/favicon.ico", "/error", "webjars/**").permitAll()
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
                        .failureUrl("/login/loginMain?error=true&type=oauth2")
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(OAuthJoinService)))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
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