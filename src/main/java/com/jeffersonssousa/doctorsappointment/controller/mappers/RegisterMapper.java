package com.jeffersonssousa.doctorsappointment.controller.mappers;

import com.jeffersonssousa.doctorsappointment.dto.register.RegisterDoctorDTO;
import com.jeffersonssousa.doctorsappointment.dto.register.RegisterPatientDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    Login toUserDoctor(RegisterDoctorDTO dto);

    Doctor toEntityDoctor(RegisterDoctorDTO dto);

    Login toUserPatient(RegisterPatientDTO dto);

    Patient toEntityPatient(RegisterPatientDTO dto);

}
