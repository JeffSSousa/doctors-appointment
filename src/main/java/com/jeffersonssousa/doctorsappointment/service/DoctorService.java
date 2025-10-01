package com.jeffersonssousa.doctorsappointment.service;

import com.jeffersonssousa.doctorsappointment.dto.DoctorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {


    @Autowired
	private DoctorRepository doctorRepository;
	
	public void insert(DoctorRequestDTO dto) {
		Doctor doctor = new Doctor(dto);
		doctorRepository.save(doctor);
	}

    public List<DoctorResponseDTO> findAll(){
        List<DoctorResponseDTO> doctors = doctorRepository.findAll().stream().map(DoctorResponseDTO::new).toList();
        return doctors;
    }
}
