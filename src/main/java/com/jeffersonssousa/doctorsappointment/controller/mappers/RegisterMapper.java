package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.register.RegisterDoctorDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    Login toUserDoctor(RegisterDoctorDTO dto);


    Doctor toEntityDoctor(RegisterDoctorDTO dto);

}
