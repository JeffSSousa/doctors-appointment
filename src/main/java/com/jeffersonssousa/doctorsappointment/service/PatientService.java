package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;
	
	public Patient insert(Patient patient) {

        Login user = userRepository.findByEmail(patient.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado usuario com esse email"));

        if(patientRepository.existsByLogin_UserId(user.getUserId())){
            throw new IllegalArgumentException("Já existe um paciente vinculado com esse acesso");
        }

        if(patientRepository.existsByCpf(patient.getCpf())){
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }

        patient.setLogin(user);
        return patientRepository.save(patient);
	}
}
