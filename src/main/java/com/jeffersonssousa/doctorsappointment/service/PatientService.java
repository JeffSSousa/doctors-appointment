package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

import java.util.UUID;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;
	
	public Patient insert(Patient patient) {

        UUID userId = patient.getLogin().getUserId();

        if(patientRepository.existsByLogin_UserId(userId)){
            throw new IllegalArgumentException("Já existe um paciente vinculado com esse acesso");
        }

        if(patientRepository.existsByCpf(patient.getCpf())){
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }

        return patientRepository.save(patient);
	}
}
