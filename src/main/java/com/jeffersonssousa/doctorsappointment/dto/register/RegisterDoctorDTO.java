package com.jeffersonssousa.doctorsappointment.dto.register;

import java.util.List;

public record RegisterDoctorDTO(
                                String username,
                                String email,
                                String password,

                                String name,
                                String phone,
                                String crm,
                                String specialty
    ) {
}
