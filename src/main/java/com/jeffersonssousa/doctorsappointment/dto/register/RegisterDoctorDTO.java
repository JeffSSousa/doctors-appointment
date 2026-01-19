package com.jeffersonssousa.doctorsappointment.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterDoctorDTO(
                                @NotBlank(message = "Campo Obrigatorio")
                                String username,
                                @Email
                                @NotBlank(message = "Campo Obrigatorio")
                                String email,
                                @NotBlank(message = "Campo Obrigatorio")
                                String password,

                                @NotBlank
                                @Size(max = 50, message = "Campo de nome fora do padrão")
                                String name,
                                @NotBlank
                                @Pattern(regexp = "\\d{10,11}", message = "O numero de telefone deve ter 10 ou 11 digitos")
                                String phone,
                                @NotBlank
                                @Pattern(regexp = "\\d{4,6}", message = "O CRM(Conselho Regional de Medicina) pode ter de 4 a 6 digitos")
                                String crm,
                                @NotBlank
                                @Size(max = 50, message = "Campo de especialidade fora do padrão")
                                String specialty
    ) {
}
