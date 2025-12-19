package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
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



        if(userRepository.findByLogin(login.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse Login. Tente Novamente!");
        }

        if(userRepository.findByEmail(login.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse e-mail, tente novamente!!");
        }

        String password = login.getPassword();
        login.setPassword(encoder.encode(password));
        userRepository.save(login);
    }

    public Login getByLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Entidade com o login " + login + " não foi encontrado"));
    }

    public Login getByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Entidade com o email " + email + " não foi encontrado"));
    }


}
