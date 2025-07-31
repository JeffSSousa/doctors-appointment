package com.jeffersonssousa.doctorsappointment.entity;

import java.util.Objects;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	private String name;
	private String email;
    private String phone;
    private String crm; // Registro profissional
    private String specialty;
    
	public Doctor() {
	}

	public Doctor(Long doctorId, String name, String email, String phone, String crm, String specialty) {
		this.doctorId = doctorId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.crm = crm;
		this.specialty = specialty;
	}
	
	public Doctor(DoctorRequestDTO dto) {
		this.name = dto.name();
		this.email = dto.email();
		this.phone = dto.phone();
		this.crm = dto.crm();
		this.specialty = dto.specialty();
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(doctorId, other.doctorId);
	}
	
    
    
}
