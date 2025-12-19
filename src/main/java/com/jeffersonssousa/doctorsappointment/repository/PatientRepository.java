package com.jeffersonssousa.doctorsappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.doctorsappointment.entity.Patient;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

    boolean existsByLogin_UserId(UUID id);

    boolean existsByCpf(String cpf);
}
