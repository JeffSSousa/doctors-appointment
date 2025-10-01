package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.dto.DoctorRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
	
	@Mock
	private DoctorRepository repository;

	@InjectMocks
	private DoctorService service;
	
	@Captor
	private ArgumentCaptor<Doctor> doctorCaptor;
	
	@Test
	@DisplayName("Deve inserir um médico corretamente.")
	void shouldCreateADoctor() {

		//Arrange
		Doctor entity = new Doctor(null,"Fulano", "fulano@email.com", "99999-9999", "5265-5", "Clinico Geral",null);

		//Act
		service.insert(entity);

		//Assert & Verify
		verify(repository, times(1)).save(doctorCaptor.capture());
		Doctor doctor = doctorCaptor.getValue();

		assertEquals(entity.getName(), doctor.getName());
		assertEquals(entity.getEmail(), doctor.getEmail());
		assertEquals(entity.getPhone(), doctor.getPhone());
		assertEquals(entity.getCrm(), doctor.getCrm());
		assertEquals(entity.getSpecialty(), doctor.getSpecialty());

	}
}
