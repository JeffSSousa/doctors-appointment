package com.jeffersonssousa.doctorsappointment.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_appoitment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appoitmentId;

	private LocalDateTime appointmentDate;

	private Integer durationInMinutes;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	public Appointment() {
	}
	
	public Appointment(AppointmentRequestDTO dto) {
		this.appointmentDate = dto.appointmentDate();
		this.durationInMinutes = dto.durationInMinutes();
	}

	public Appointment(Long id, LocalDateTime appointmentDate, Integer durationInMinutes) {
		this.appoitmentId = id;
		this.appointmentDate = appointmentDate;
		this.durationInMinutes = durationInMinutes;
	}

	public Long getId() {
		return appoitmentId;
	}

	public void setId(Long id) {
		this.appoitmentId = id;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appoitmentId);
	}

	
	
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appoitmentId, other.appoitmentId);
	}

}
