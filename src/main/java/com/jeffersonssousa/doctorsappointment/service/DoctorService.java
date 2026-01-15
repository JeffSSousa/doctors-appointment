package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.exception.DoctorAlreadyLinkedException;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public void insert(Doctor doctor) {

        UUID userId = doctor.getLogin().getUserId();

        if(doctorRepository.existsByLogin_UserId(userId)){
            throw new DoctorAlreadyLinkedException("Já existe um doutor vinculado com esse acesso");
        }

        doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
}

