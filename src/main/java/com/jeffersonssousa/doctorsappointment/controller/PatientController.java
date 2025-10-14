package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.PatientMapper;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffersonssousa.doctorsappointment.dto.patient.PatientRequestDTO;
import com.jeffersonssousa.doctorsappointment.service.PatientService;

@RestController
@RequestMapping("patient")
public class PatientController {

	@Autowired
	private PatientService service;

    @Autowired
    private PatientMapper mapper;
	
	@PostMapping
    @PreAuthorize("hasRole('ADMIN', 'RECEPTIONIST')")
	public ResponseEntity<Void> registerPatient(@RequestBody @Valid PatientRequestDTO dto){
        Patient patient = mapper.toEntity(dto);
        service.insert(patient);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
