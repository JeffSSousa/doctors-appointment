package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.User;
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

    public void createUser (User user){
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    public User getByLogin(String login){
        return userRepository.findByLogin(login);
    }

}
