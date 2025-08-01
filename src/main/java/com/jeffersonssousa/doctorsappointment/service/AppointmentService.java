package com.jeffersonssousa.doctorsappointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.AppoitmentRepository;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppoitmentRepository appoitmentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public void insert(AppointmentRequestDTO dto) {
		Doctor doctor = doctorRepository.getReferenceById(dto.doctorId());
		Patient patient = patientRepository.getReferenceById(dto.patientId());
		
		Appointment appointment = new Appointment(dto);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		appoitmentRepository.save(appointment);
		
	}
}
