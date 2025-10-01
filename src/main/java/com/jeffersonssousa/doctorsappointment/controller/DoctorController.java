package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.dto.DoctorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private DoctorService service;
	
	@PostMapping
	public ResponseEntity<Void> registerDoctor(@RequestBody @Valid DoctorRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> findAll (){
        List<DoctorResponseDTO> doctors = service.findAll();
        return ResponseEntity.ok().body(doctors);
    }

}
