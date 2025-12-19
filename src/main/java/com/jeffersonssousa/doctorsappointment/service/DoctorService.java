package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public void insert(Doctor doctor) {

        Login user = userRepository.findByEmail(doctor.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado usuario com esse email"));

        if(doctorRepository.existsByLogin_UserId(user.getUserId())){
            throw new IllegalArgumentException("Já existe um paciente vinculado com esse acesso");
        }

        doctor.setLogin(user);
        doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
}

