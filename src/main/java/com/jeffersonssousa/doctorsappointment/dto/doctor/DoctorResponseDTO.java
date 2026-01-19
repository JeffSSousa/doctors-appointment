package com.jeffersonssousa.doctorsappointment.dto.doctor;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;

public record DoctorResponseDTO(Long doctorId, String name, String email, String phone, String crm, String specialty) {

}
