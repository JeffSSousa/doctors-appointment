package com.jeffersonssousa.doctorsappointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;

@Repository
public interface AppoitmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findAllByDoctor(Doctor doctor);

}
