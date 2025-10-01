package com.jeffersonssousa.doctorsappointment.dto;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;

public record DoctorResponseDTO(Long doctorId, String name, String email, String phone, String crm, String specialty) {

    public DoctorResponseDTO (Doctor doctor){
        this(doctor.getDoctorId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty());
    }
}
