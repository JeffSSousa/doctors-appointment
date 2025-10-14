package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.DoctorMapper;
import com.jeffersonssousa.doctorsappointment.dto.doctor.DoctorResponseDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jeffersonssousa.doctorsappointment.dto.doctor.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private DoctorService service;

    @Autowired
    private DoctorMapper doctorMapper;

	@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> registerDoctor(@RequestBody @Valid DoctorRequestDTO dto){
        Doctor doctor = doctorMapper.toEntity(dto);
        service.insert(doctor);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DoctorResponseDTO>> findAll (){
        List<DoctorResponseDTO> doctors = service.findAll()
                                                    .stream()
                                                    .map(doctorMapper::toResponseDTO)
                                                    .toList();

        return ResponseEntity.ok().body(doctors);
    }

}
