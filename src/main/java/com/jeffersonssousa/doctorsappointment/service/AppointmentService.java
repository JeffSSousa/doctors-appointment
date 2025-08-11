package com.jeffersonssousa.doctorsappointment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.dto.AppointmentResponseDTO;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.AppoitmentRepository;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentService {

	@Autowired
	private AppoitmentRepository appoitmentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public void insert(AppointmentRequestDTO dto) {
		
		if(dto.appointmentDate().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("A data tem que ser futura!!");
		}
		
		
		Doctor doctor = doctorRepository.getReferenceById(dto.doctorId());
		Patient patient = patientRepository.getReferenceById(dto.patientId());
		
		Appointment appointment = new Appointment(dto);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		if(appointment.isReturn()) {
			appointment.setDurationInMinutes(15);
		} else {
			appointment.setDurationInMinutes(30);
		}
		
		
	    LocalDateTime startDateTime = appointment.getAppointmentDate();
	    LocalDateTime endDateTime = startDateTime.plusMinutes(appointment.getDurationInMinutes());

	    validateScheduleConflict(doctor, startDateTime, endDateTime);
		
		appoitmentRepository.save(appointment);
		
	}

	public List<AppointmentResponseDTO> listAppointmentByDoctor(Long id) {
		Doctor doctor = doctorRepository.findById(id)
				        .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado um Doctor com esse id: " + id));
		
		List<AppointmentResponseDTO> list = appoitmentRepository.findAllByDoctor(doctor).stream().map(AppointmentResponseDTO::new).toList();
		
		return list;
	}
	
	
	
	
	private void validateScheduleConflict(Doctor doctor, LocalDateTime startDateTime, LocalDateTime endDateTime) {
	    List<Appointment> appointments = appoitmentRepository.findAllByDoctorAndAppointmentDateBetween(
	        doctor,
	        startDateTime.toLocalDate().atStartOfDay(),
	        startDateTime.toLocalDate().atTime(23, 59, 59)
	    );

	    boolean hasConflict = appointments.stream()
	        .anyMatch(existing -> isOverlapping(
	            existing.getAppointmentDate(),
	            existing.getAppointmentDate().plusMinutes(existing.getDurationInMinutes()),
	            startDateTime,
	            endDateTime
	        ));

	    if (hasConflict) {
	        throw new IllegalArgumentException("Já existe uma consulta para este médico nesse horário.");
	    }
	}

	private boolean isOverlapping(LocalDateTime start1, LocalDateTime end1,
	                              LocalDateTime start2, LocalDateTime end2) {
	    return !start1.isAfter(end2) && !start2.isAfter(end1);
	}
	
}
