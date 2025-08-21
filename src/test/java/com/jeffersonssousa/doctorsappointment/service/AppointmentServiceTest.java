package com.jeffersonssousa.doctorsappointment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.doctorsappointment.dto.AppointmentRequestDTO;
import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import com.jeffersonssousa.doctorsappointment.repository.AppoitmentRepository;
import com.jeffersonssousa.doctorsappointment.repository.DoctorRepository;
import com.jeffersonssousa.doctorsappointment.repository.PatientRepository;

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
	class insert{
		
		@Test
		@DisplayName("Deve inserir uma consulta com sucesso")
		void shouldCreateAAppointment() {
			
			//Arrange
			LocalDateTime date = LocalDateTime.now().plusDays(1);
			Doctor doctor = new Doctor();
			Patient patient = new Patient();
			AppointmentRequestDTO dto = new AppointmentRequestDTO(date, 1L, 1L, false);
			
			when(doctorRepository.getReferenceById(anyLong())).thenReturn(doctor);
			when(patientRepository.getReferenceById(anyLong())).thenReturn(patient);
			
			//Act
			service.insert(dto);
			
			//Assert 
			verify(doctorRepository, times(1)).getReferenceById(anyLong());
			verify(patientRepository, times(1)).getReferenceById(anyLong());
			verify(appointmentRepository, times(1)).save(appointmentCaptor.capture());
			
			Appointment appointment = appointmentCaptor.getValue();
			
			
			//Verify
			assertEquals(dto.appointmentDate(), appointment.getAppointmentDate());
			assertEquals(30, appointment.getDurationInMinutes());
			assertEquals(doctor.getDoctorId(), appointment.getDoctor().getDoctorId());
			assertEquals(patient.getPatientId(), appointment.getPatient().getPatientId());
		}
		
	
		@Test
		@DisplayName("Deve lançar exceção quando a data for no passado")
		void shouldThrowExceptionWhenDateIsInThePast() {
			
			//Arrange
			LocalDateTime date = LocalDateTime.now().minusDays(1);
			AppointmentRequestDTO dto = new AppointmentRequestDTO(date, 1L, 1L, false);
			
			//Act
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> service.insert(dto));
			
			//Assert
			assertEquals("A data tem que ser futura!!", e.getMessage());	
		}
		
		@Test
		@DisplayName("Deve lançar uma exceção caso o médico ou o paciente não tenha sido encontrado")
		void shouldThrowExceptionWhenDoctorOrPatientNotFound() {
			
		}
		
	}
}
