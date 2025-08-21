package com.jeffersonssousa.doctorsappointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.dto.PatientRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public Patient insert(PatientRequestDTO dto) {
		Patient patient = new Patient(dto);
		return patientRepository.save(patient);
	}
	
}
