package com.jeffersonssousa.doctorsappointment.dto;

import java.time.LocalDate;

public record PatientRequestDTO(String name,
								String cpf,
								LocalDate birthDate,
								String sex,
								Integer weight,
								Double height) {

}
