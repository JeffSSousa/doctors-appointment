package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public void registerDoctor(Login user, Doctor doctor) {

        List<String> roles = List.of("DOCTOR");
        user.setRoles(roles);
        user = userService.createUser(user);

        doctor.setLogin(user);
        doctorService.insert(doctor);
    }

    public void registerPatient(Login user, Patient patient){

        List<String> roles = List.of("PATIENT");
        user.setRoles(roles);
        user = userService.createUser(user);

        patient.setLogin(user);
        patientService.insert(patient);

    }

}
