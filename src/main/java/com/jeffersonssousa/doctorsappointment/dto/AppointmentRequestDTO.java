package com.jeffersonssousa.doctorsappointment.dto;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(LocalDateTime appointmentDate,
		                           Integer durationInMinutes,
		                           Long doctorId,
		                           Long patientId) {

}
