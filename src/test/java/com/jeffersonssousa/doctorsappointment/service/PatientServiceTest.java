package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.UUID;

import com.jeffersonssousa.doctorsappointment.builders.PatientTestBuilder;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.exception.business.PatientAlreadyLinkedException;
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

	@Test
	@DisplayName("Deve inserir um paciente corretamente")
	void shouldCreateAPatient() {

		// Arrange
        Login login = new Login();
        login.setUserId(UUID.randomUUID());

        Patient patient = PatientTestBuilder
                        .aPatient()
                        .withLogin(login)
                        .build();

		when(repository.save(any(Patient.class))).thenReturn(patient);
        when(repository.existsByLogin_UserId(any(UUID.class))).thenReturn(false);
        when(repository.existsByCpf(anyString())).thenReturn(false);

		// Act
		Patient returnedPatient = service.insert(patient);


		// Assert & Verify
		verify(repository, times(1)).save(any(Patient.class));
        verify(repository, times(1)).existsByLogin_UserId(patient.getLogin().getUserId());
        verify(repository, times(1)).existsByCpf(patient.getCpf());

		assertEquals(patient.getName(), returnedPatient.getName());
		assertEquals(patient.getCpf(), returnedPatient.getCpf());
		assertEquals(patient.getBirthDate(), returnedPatient.getBirthDate());
	}


    @Test
    @DisplayName("Deve lançar exceção quando usuário já estiver vinculado a um paciente")
    void shouldThrowExceptionWhenUserAlreadyLinked(){

        Login login = new Login();
        UUID userId = UUID.randomUUID();
        login.setUserId(userId);

        Patient patient = PatientTestBuilder
                .aPatient()
                .withLogin(login)
                .build();

        when(repository.existsByLogin_UserId(userId)).thenReturn(true);

        PatientAlreadyLinkedException e = assertThrows(PatientAlreadyLinkedException.class,
                () -> service.insert(patient));

        verify(repository, never()).save(any());

        assertEquals("Já existe um paciente vinculado com esse acesso", e.getMessage());
    }


    @Test
    @DisplayName("Deve lançar exceção quando CPF já estiver cadastrado")
    void shouldThrowExceptionWhenCpfAlreadyExists() {

        Login login = new Login();
        login.setUserId(UUID.randomUUID());

        Patient patient = PatientTestBuilder
                .aPatient()
                .withLogin(login)
                .build();

        when(repository.existsByLogin_UserId(any())).thenReturn(false);
        when(repository.existsByCpf(patient.getCpf())).thenReturn(true);

        PatientAlreadyLinkedException e = assertThrows(PatientAlreadyLinkedException.class,
                () -> service.insert(patient));

        verify(repository, never()).save(any());

        assertEquals("Já existe um paciente cadastrado com esse CPF",e.getMessage());
    }
}
