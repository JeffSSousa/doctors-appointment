package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.dto.PatientRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	@Mock
	private PatientRepository repository;

	@InjectMocks
	private PatientService service;

	@Captor
	private ArgumentCaptor<Patient> patientCaptor;

	@Test
	@DisplayName("Deve inserir um paciente corretamente")
	void shouldCreateAPatient() {

		// Arrange
		PatientRequestDTO dto = new PatientRequestDTO(
				"Ciclano", 
				"999.999.999-99", 
				LocalDate.parse("1999-05-08"),
				"Masculino", 
				72, 
				1.62
				);
		
		Patient savedPatient = new Patient(dto);
		savedPatient.setPatientId(1L);

		when(repository.save(any(Patient.class))).thenReturn(savedPatient);

		
		// Act
		Patient returnedPatient = service.insert(dto);

		
		// Assert & Verify
		verify(repository, times(1)).save(patientCaptor.capture());

		assertEquals(dto.name(), returnedPatient.getName());
		assertEquals(dto.cpf(), returnedPatient.getCpf());
		assertEquals(dto.birthDate(), returnedPatient.getBirthDate());
		assertEquals(dto.sex(), returnedPatient.getSex());
		assertEquals(dto.weight(), returnedPatient.getWeight());
		assertEquals(dto.height(), returnedPatient.getHeight());
	}
}
