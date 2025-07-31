package com.jeffersonssousa.doctorsappointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;
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

}
