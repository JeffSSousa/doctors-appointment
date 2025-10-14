package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.user.UserRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Login toEntity (UserRequestDTO dto);
}
