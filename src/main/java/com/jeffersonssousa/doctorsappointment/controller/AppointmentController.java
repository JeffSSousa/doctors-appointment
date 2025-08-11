package com.jeffersonssousa.doctorsappointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping
	public ResponseEntity<Void> scheduleAppointment(@RequestBody AppointmentRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<AppointmentResponseDTO>> listAppointmentByDoctor(@PathVariable Long id){
		List<AppointmentResponseDTO> list = service.listAppointmentByDoctor(id);
		return ResponseEntity.ok().body(list);
	}

}
