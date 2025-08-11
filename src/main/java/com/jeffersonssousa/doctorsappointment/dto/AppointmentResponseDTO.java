package com.jeffersonssousa.doctorsappointment.dto;

import java.time.LocalDateTime;

import com.jeffersonssousa.doctorsappointment.entity.Appointment;

public record AppointmentResponseDTO (String patient, boolean isReturn , LocalDateTime appointmentDate, Integer durationInMinutes) {
	
	public AppointmentResponseDTO(Appointment appointment) {
		this(appointment.getPatient().getName(), appointment.isReturn(), appointment.getAppointmentDate(), appointment.getDurationInMinutes());
	}
	
}
