package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser (Login login){
        String password = login.getPassword();
        login.setPassword(encoder.encode(password));
        userRepository.save(login);
    }

    public Login getByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public Login getByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
