package com.jeffersonssousa.doctorsappointment.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jeffersonssousa.doctorsappointment.exception.AppointmentConflictException;
import com.jeffersonssousa.doctorsappointment.exception.InvalidAppointmentDateException;
import com.jeffersonssousa.doctorsappointment.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void insert(Appointment appointment, Long doctorId, Long patientId) {
		
		if(appointment.getAppointmentDate().isBefore(LocalDateTime.now())) {
			throw new InvalidAppointmentDateException("A data tem que ser futura!!");
		}

		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new UserNotFoundException("Médico não foi encontrado!!"));
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new UserNotFoundException("Paciente não foi encontrado!!"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
		
		if(appointment.isReturn()) {
			appointment.setDurationInMinutes(15);
		} else {
			appointment.setDurationInMinutes(30);
		}

	    LocalDateTime startDateTime = appointment.getAppointmentDate();
	    LocalDateTime endDateTime = startDateTime.plusMinutes(appointment.getDurationInMinutes());

	    validateScheduleConflict(appointment.getDoctor(), startDateTime, endDateTime);
		
		appoitmentRepository.save(appointment);
	}

	public List<Appointment> listAppointmentByDoctor(Long id) {
		Doctor doctor = doctorRepository.findById(id)
				        .orElseThrow(() -> new UserNotFoundException("Não foi encontrado um Doctor com esse id: " + id));
		
		return appoitmentRepository.findAllByDoctor(doctor);
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
	        throw new AppointmentConflictException("Já existe uma consulta para este médico nesse horário.");
	    }
	}

	private boolean isOverlapping(LocalDateTime start1, LocalDateTime end1,
	                              LocalDateTime start2, LocalDateTime end2) {
	    return !start1.isAfter(end2) && !start2.isAfter(end1);
	}
	
}
