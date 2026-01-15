package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.exception.EmailAlreadyInUseException;
import com.jeffersonssousa.doctorsappointment.exception.UserAlreadyExistsException;
import com.jeffersonssousa.doctorsappointment.exception.UserNotFoundException;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Login createUser (Login login){

        if(userRepository.findByUsername(login.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com esse username. Tente Novamente!");
        }

        if(userRepository.findByEmail(login.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException("Já existe um usuário com esse e-mail, tente novamente!!");
        }

        String password = login.getPassword();
        login.setPassword(encoder.encode(password));

        return userRepository.save(login);
    }

    public Login getByLogin(String login){
        return userRepository.findByUsername(login)
                .orElseThrow(() -> new UserNotFoundException("Entidade com o login " + login + " não foi encontrado"));
    }

    public Optional<Login> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
