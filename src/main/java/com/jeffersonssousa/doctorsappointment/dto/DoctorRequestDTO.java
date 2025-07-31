package com.jeffersonssousa.doctorsappointment.dto;

public record DoctorRequestDTO(String name,
							   String email,
							   String phone,
							   String crm,
							   String specialty) {

}
