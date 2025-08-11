package com.jeffersonssousa.doctorsappointment.dto;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(LocalDateTime appointmentDate,
		                           Long doctorId,
		                           Long patientId,
		                           boolean isReturn) {

}
