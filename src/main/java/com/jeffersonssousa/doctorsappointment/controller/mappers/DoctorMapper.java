package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.dto.DoctorResponseDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorRequestDTO dto);

    DoctorResponseDTO toResponseDTO (Doctor doctor);
}
