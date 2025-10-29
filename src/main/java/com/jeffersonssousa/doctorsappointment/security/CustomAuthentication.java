package com.jeffersonssousa.doctorsappointment.security;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomAuthentication implements Authentication {

    @Autowired
    private Login login;

    public CustomAuthentication(Login user) {
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.login
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return login;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return login.getLogin();
    }
}
