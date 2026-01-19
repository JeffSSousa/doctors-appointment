package com.jeffersonssousa.doctorsappointment.dto.appointment;

import java.time.LocalDateTime;

import com.jeffersonssousa.doctorsappointment.entity.Appointment;

public record AppointmentResponseDTO (String patient, boolean isReturn , LocalDateTime appointmentDate, Integer durationInMinutes) {

}
