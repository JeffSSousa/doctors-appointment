package com.jeffersonssousa.doctorsappointment.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "patientId")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	private String name;
	private String cpf;
	private LocalDate birthDate;
	private String sex;
	private Integer weight;
	private Double height;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}
