package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.appointment.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.dto.appointment.AppointmentResponseDTO;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface AppointmentMapper {

    Appointment toEntity (AppointmentRequestDTO dto);

    @Mapping(target = "patient", expression = "java(appointment.getPatient().getName())")
    AppointmentResponseDTO toDto(Appointment appointment);
}
