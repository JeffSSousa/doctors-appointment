package com.jeffersonssousa.doctorsappointment.security;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    @Autowired
    private UserService userService;

    public Login getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication customAuth){
            return customAuth.getLogin();
        }

        return null;
    }

}
