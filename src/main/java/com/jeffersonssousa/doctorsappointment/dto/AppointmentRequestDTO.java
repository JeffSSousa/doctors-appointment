package com.jeffersonssousa.doctorsappointment.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(@NotNull
                                    LocalDateTime appointmentDate,
                                    @NotNull(message = "Campo Obrigatorio")
		                            Long doctorId,
                                    @NotNull(message = "Campo Obrigatorio")
		                            Long patientId,
		                            boolean isReturn) {

}
