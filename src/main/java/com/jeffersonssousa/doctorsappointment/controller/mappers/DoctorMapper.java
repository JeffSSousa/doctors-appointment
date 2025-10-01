package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.dto.DoctorResponseDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(source = "name", target = "name")
    Doctor toEntity(DoctorRequestDTO dto);

    DoctorResponseDTO toResponseDTO (Doctor doctor);
}
