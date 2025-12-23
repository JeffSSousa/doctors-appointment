package com.jeffersonssousa.doctorsappointment.dto.register;

import java.time.LocalDate;

public record RegisterPatientDTO(
                                String username,
                                String email,
                                String password,

                                String name,
                                String cpf,
                                LocalDate birthDate
    ) {
}