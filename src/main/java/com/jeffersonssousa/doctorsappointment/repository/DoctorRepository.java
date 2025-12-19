package com.jeffersonssousa.doctorsappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

    boolean existsByLogin_UserId(UUID userId);
}
