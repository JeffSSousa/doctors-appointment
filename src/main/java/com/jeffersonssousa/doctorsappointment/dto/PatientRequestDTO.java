package com.jeffersonssousa.doctorsappointment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PatientRequestDTO(@NotBlank
                                @Size(max = 50, message = "Campo de nome preenchido fora do padrão")
                                String name,
                                @CPF
                                @NotBlank
								String cpf,
                                @NotNull
								LocalDate birthDate,
                                @NotBlank
                                @Size(max = 9)
								String sex,
                                @NotNull
								Integer weight,
                                @NotNull
								Double height) {

}
