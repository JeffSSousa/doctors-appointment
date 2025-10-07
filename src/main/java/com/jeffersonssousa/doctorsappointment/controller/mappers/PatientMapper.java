package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    default String toName(Patient patient) {
        return patient != null ? patient.getName() : null;
    }
}
