package com.jeffersonssousa.doctorsappointment.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RegisterPatientDTO(
                                @NotBlank(message = "Campo Obrigatorio")
                                String username,
                                @Email
                                @NotBlank(message = "Campo Obrigatorio")
                                String email,
                                @NotBlank(message = "Campo Obrigatorio")
                                String password,

                                @NotBlank
                                @Size(max = 50, message = "Campo de nome preenchido fora do padrão")
                                String name,
                                @CPF
                                @NotBlank
                                String cpf,
                                @NotNull
                                LocalDate birthDate
    ) {
}