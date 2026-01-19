package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.jeffersonssousa.doctorsappointment.builders.AppointmentTestBuilder;
import com.jeffersonssousa.doctorsappointment.builders.DoctorTestBuilder;
import com.jeffersonssousa.doctorsappointment.builders.PatientTestBuilder;
import com.jeffersonssousa.doctorsappointment.exception.business.InvalidAppointmentDateException;
import com.jeffersonssousa.doctorsappointment.exception.business.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.dto.appointment.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.AppoitmentRepository;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

	@Mock
	private AppoitmentRepository appointmentRepository;

	@Mock
	private DoctorRepository doctorRepository;

	@Mock
	private PatientRepository patientRepository;

	@InjectMocks
	private AppointmentService service;

	@Captor
	private ArgumentCaptor<Appointment> appointmentCaptor;

	@Nested
	class insert {


		@Test
		@DisplayName("Deve inserir uma consulta com sucesso")
		void shouldCreateAAppointment() {

			// Arrange
            Doctor doctor = DoctorTestBuilder
                                .aDoctor()
                                .withDoctorId(1L)
                                .build();

            Patient patient = PatientTestBuilder
                                .aPatient()
                                .withPatientId(1L)
                                .build();

            Appointment appointment = AppointmentTestBuilder
                                        .anAppointment()
                                        .withDoctor(doctor)
                                        .withPatient(patient)
                                        .build();

			when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));
			when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
            when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

			// Act
			service.insert(appointment, doctor.getDoctorId(), patient.getPatientId());

			// Assert
			verify(doctorRepository, times(1)).findById(anyLong());
			verify(patientRepository, times(1)).findById(anyLong());
			verify(appointmentRepository, times(1)).save(appointmentCaptor.capture());

			Appointment capturedAppointment = appointmentCaptor.getValue();

			// Verify
			assertEquals(appointment.getAppointmentDate(), capturedAppointment.getAppointmentDate());
			assertEquals(30, capturedAppointment.getDurationInMinutes());
			assertEquals(doctor.getDoctorId(), capturedAppointment.getDoctor().getDoctorId());
			assertEquals(patient.getPatientId(), capturedAppointment.getPatient().getPatientId());
		}


		@Test
		@DisplayName("Deve lançar exceção quando a data for no passado")
		void shouldThrowExceptionWhenDateIsInThePast() {

			// Arrange
            Doctor doctor = DoctorTestBuilder
                    .aDoctor()
                    .withDoctorId(1L)
                    .build();

            Patient patient = PatientTestBuilder
                    .aPatient()
                    .withPatientId(1L)
                    .build();

            Appointment appointment = AppointmentTestBuilder
                    .anAppointment()
                    .withDoctor(doctor)
                    .withPatient(patient)
                    .withAppointmentDate(LocalDateTime.now().minusNanos(1))
                    .build();


			// Act
            InvalidAppointmentDateException e = assertThrows(InvalidAppointmentDateException.class,
                                                            () -> service.insert(appointment,
                                                                                doctor.getDoctorId(),
                                                                                patient.getPatientId()
                                                            ));

			// Assert
			assertEquals("A data tem que ser futura!!", e.getMessage());
		}


		@Test
		@DisplayName("Deve lançar uma exceção caso o médico não tenha sido encontrado")
		void shouldThrowExceptionWhenDoctorNotFound() {

			//Arrange
            Doctor doctor = DoctorTestBuilder
                    .aDoctor()
                    .withDoctorId(1L)
                    .build();

            Patient patient = PatientTestBuilder
                    .aPatient()
                    .withPatientId(1L)
                    .build();

            Appointment appointment = AppointmentTestBuilder
                    .anAppointment()
                    .withDoctor(doctor)
                    .withPatient(patient)
                    .build();

			when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

			// Act & Assert
			UserNotFoundException e = assertThrows(UserNotFoundException.class,
                                            () -> service.insert(appointment,
                                                                doctor.getDoctorId(),
                                                                patient.getPatientId()
                                            ));

			assertEquals("Médico não foi encontrado!!", e.getMessage());
		}

		@Test
		@DisplayName("Deve lançar uma exceção caso o paciente não tenha sido encontrado")
		void shouldThrowExceptionWhenPatientNotFound() {

            //Arrange
            Doctor doctor = DoctorTestBuilder
                    .aDoctor()
                    .withDoctorId(1L)
                    .build();

            Patient patient = PatientTestBuilder
                    .aPatient()
                    .withPatientId(1L)
                    .build();

            Appointment appointment = AppointmentTestBuilder
                    .anAppointment()
                    .withDoctor(doctor)
                    .withPatient(patient)
                    .build();
			
			when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
			when(patientRepository.findById(1L)).thenReturn(Optional.empty());

			// Act & Assert
			UserNotFoundException e = assertThrows(UserNotFoundException.class,
                                            () -> service.insert(appointment,
                                                                doctor.getDoctorId(),
                                                                patient.getPatientId()
                                            ));


            assertEquals("Paciente não foi encontrado!!", e.getMessage());
		}
	}
}
