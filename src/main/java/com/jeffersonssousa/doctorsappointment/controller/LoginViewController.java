package com.jeffersonssousa.doctorsappointment.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String pageLogin(){
        return "login";
    }

    @GetMapping("/")
    public String homePage(Authentication authentication) {

        if (authentication == null
                || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(Authentication authentication) {
        return "Olá " + authentication.getName();
    }

}
