package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jeffersonssousa.doctorsappointment.builders.DoctorTestBuilder;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.exception.business.DoctorAlreadyLinkedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
	
	@Mock
	private DoctorRepository repository;

	@InjectMocks
	private DoctorService service;
	
	@Captor
	private ArgumentCaptor<Doctor> doctorCaptor;



    @Nested
    class insert {

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

    @Nested
    class findAll{

        @Test
        @DisplayName("Deve buscar todos os Médicos com sucesso")
        void shouldFindAllDoctorsWithSuccess(){

            Doctor doctor1 = DoctorTestBuilder.aDoctor()
                                                .withDoctorId(1L)
                                                .build();

            Doctor doctor2 = DoctorTestBuilder.aDoctor()
                                                .withDoctorId(2L)
                                                .build();

            List<Doctor> list = Arrays.asList(doctor1,doctor2);

            when(repository.findAll()).thenReturn(list);


            List<Doctor> returnedList = service.findAll();


            verify(repository, times(1)).findAll();

            assertNotNull(returnedList);
            assertEquals(list.size(),returnedList.size());
            assertEquals(list.getFirst().getDoctorId(),returnedList.getFirst().getDoctorId());

        }

    }

}
