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

//	@Test
//	@DisplayName("Deve inserir um paciente corretamente")
//	void shouldCreateAPatient() {
//
//		// Arrange
//        String name = "Ciclano";
//        String cpf = "999.999.999-99";
//        LocalDate birthDate = LocalDate.parse("1999-05-08");
//        String sex = "Masculino";
//        Integer weight = 72;
//        Double height = 1.62;
//
//        Patient patient = new Patient(null, name, cpf, birthDate, sex, weight, height, null);
//
//		when(repository.save(any(Patient.class))).thenReturn(patient);
//
//
//		// Act
//		Patient returnedPatient = service.insert(patient);
//
//
//		// Assert & Verify
//		verify(repository, times(1)).save(patientCaptor.capture());
//
//		assertEquals(patient.getName(), returnedPatient.getName());
//		assertEquals(patient.getCpf(), returnedPatient.getCpf());
//		assertEquals(patient.getBirthDate(), returnedPatient.getBirthDate());
//		assertEquals(patient.getSex(), returnedPatient.getSex());
//		assertEquals(patient.getWeight(), returnedPatient.getWeight());
//		assertEquals(patient.getHeight(), returnedPatient.getHeight());
//	}
}
