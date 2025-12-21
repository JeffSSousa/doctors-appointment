package com.jeffersonssousa.doctorsappointment.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRequestDTO(
        @NotBlank(message = "Campo Obrigatorio")
        String username,
        @Email(message = "Email invalido")
        @NotBlank(message = "Campo Obrigatorio")
        String email,
        @NotBlank(message = "Campo Obrigatorio")
        String password,
        List<String> roles) {
}
