package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.jeffersonssousa.doctorsappointment.builders.DoctorTestBuilder;
import com.jeffersonssousa.doctorsappointment.builders.PatientTestBuilder;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.exception.business.DoctorAlreadyLinkedException;
import com.jeffersonssousa.doctorsappointment.exception.business.PatientAlreadyLinkedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.UUID;

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
        Login login = new Login();
        UUID userId = UUID.randomUUID();
        login.setUserId(userId);

        Doctor doctor = DoctorTestBuilder
                            .aDoctor()
                            .withLogin(login)
                            .build();

        when(repository.save(any(Doctor.class))).thenReturn(doctor);
        when(repository.existsByLogin_UserId(any(UUID.class))).thenReturn(false);

		//Act
		service.insert(doctor);

		//Assert & Verify
        verify(repository,times(1)).existsByLogin_UserId(any(UUID.class));
		verify(repository, times(1)).save(doctorCaptor.capture());

		Doctor returnedDoctor = doctorCaptor.getValue();

		assertEquals(doctor.getName(), returnedDoctor.getName());
		assertEquals(doctor.getEmail(), returnedDoctor.getEmail());
		assertEquals(doctor.getPhone(), returnedDoctor.getPhone());
		assertEquals(doctor.getCrm(), returnedDoctor.getCrm());
		assertEquals(doctor.getSpecialty(), returnedDoctor.getSpecialty());

	}

    @Test
    @DisplayName("Deve lançar exceção quando o usuario já estiver vinculado com um médico")
    void shouldThrowExceptionWhenUserAlreadyLinked(){

        Login login = new Login();
        UUID userId = UUID.randomUUID();
        login.setUserId(userId);

        Doctor doctor = DoctorTestBuilder
                .aDoctor()
                .withLogin(login)
                .build();

        when(repository.existsByLogin_UserId(any(UUID.class))).thenReturn(true);

        DoctorAlreadyLinkedException e = assertThrows(DoctorAlreadyLinkedException.class,
                () -> service.insert(doctor));

        verify(repository, never()).save(any());

        assertEquals("Já existe um doutor vinculado com esse acesso", e.getMessage());

    }
}
