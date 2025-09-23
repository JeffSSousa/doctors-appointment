package com.jeffersonssousa.doctorsappointment.entity;

import java.util.List;
import java.util.Objects;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "doctorId")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	private String name;
	private String email;
    private String phone;
    private String crm; // Registro profissional
    private String specialty;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

	public Doctor(DoctorRequestDTO dto) {
		this.name = dto.name();
		this.email = dto.email();
		this.phone = dto.phone();
		this.crm = dto.crm();
		this.specialty = dto.specialty();
	}

}
