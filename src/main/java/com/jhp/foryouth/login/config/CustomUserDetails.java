package com.jhp.foryouth.login.config;

import com.jhp.foryouth.user.domain.UserAuth;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
public class CustomUserDetails implements UserDetails {

    private final String userId;
    private final String userPw;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserAuth userAuth, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userAuth.getUserId();
        this.userPw = userAuth.getUserPw();
        this.email = userAuth.getUser().getUserEmail();
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
