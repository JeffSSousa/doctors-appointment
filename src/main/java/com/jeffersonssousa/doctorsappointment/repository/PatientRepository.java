package com.jeffersonssousa.doctorsappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.doctorsappointment.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
