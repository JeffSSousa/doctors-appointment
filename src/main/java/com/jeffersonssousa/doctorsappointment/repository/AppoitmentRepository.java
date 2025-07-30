package com.jeffersonssousa.doctorsappointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.doctorsappointment.entity.Appoitment;

@Repository
public interface AppoitmentRepository extends JpaRepository<Appoitment, Long>{

}
