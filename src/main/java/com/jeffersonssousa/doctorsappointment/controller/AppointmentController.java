package com.jeffersonssousa.doctorsappointment.controller;

import java.util.List;

import com.jeffersonssousa.doctorsappointment.controller.mappers.AppointmentMapper;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.dto.AppointmentResponseDTO;
import com.jeffersonssousa.doctorsappointment.service.AppointmentService;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentService service;

    @Autowired
    private AppointmentMapper mapper;

	@PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Void> scheduleAppointment(@RequestBody @Valid AppointmentRequestDTO dto){
        Appointment appointment = mapper.toEntity(dto);
		service.insert(appointment, dto.doctorId(), dto.patientId());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AppointmentResponseDTO>> listAppointmentByDoctor(@PathVariable Long id){
		List<AppointmentResponseDTO> list = service.listAppointmentByDoctor(id).stream().map(mapper::toDto).toList();
		return ResponseEntity.ok().body(list);
	}
}
