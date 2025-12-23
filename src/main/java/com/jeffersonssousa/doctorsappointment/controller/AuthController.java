package com.jeffersonssousa.doctorsappointment.controller;

import com.jeffersonssousa.doctorsappointment.controller.mappers.RegisterMapper;
import com.jeffersonssousa.doctorsappointment.dto.register.RegisterDoctorDTO;
import com.jeffersonssousa.doctorsappointment.dto.register.RegisterPatientDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private RegisterMapper mapper;

    @PostMapping("/doctor")
    public ResponseEntity<Void> registerDoctor(@RequestBody RegisterDoctorDTO dto){

        Login user = mapper.toUserDoctor(dto);
        Doctor doctor =  mapper.toEntityDoctor(dto);
        service.registerDoctor(user,doctor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/patient")
    public ResponseEntity<Void> registerPatient(@RequestBody RegisterPatientDTO dto){

        Login user = mapper.toUserPatient(dto);
        Patient patient = mapper.toEntityPatient(dto);
        service.registerPatient(user,patient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
