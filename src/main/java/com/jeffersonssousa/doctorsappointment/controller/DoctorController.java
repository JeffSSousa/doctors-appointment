package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.DoctorMapper;
import com.jeffersonssousa.doctorsappointment.dto.doctor.DoctorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.jeffersonssousa.doctorsappointment.service.DoctorService;
import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private DoctorService service;

    @Autowired
    private DoctorMapper doctorMapper;

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
