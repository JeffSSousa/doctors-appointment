package com.jeffersonssousa.doctorsappointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void insert(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }
}

