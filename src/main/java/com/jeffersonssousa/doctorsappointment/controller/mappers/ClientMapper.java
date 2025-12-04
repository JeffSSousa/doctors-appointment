package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.ClientRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDTO dto);
}
