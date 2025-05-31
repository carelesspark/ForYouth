package com.jhp.foryouth.login.service;

import com.jhp.foryouth.user.domain.UserAuth;
import com.jhp.foryouth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserAuth user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자 정보를 찾을 수 없습니다."));

        return new User(user.getUserId(), user.getUserPw(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserAuth userAuth) {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
