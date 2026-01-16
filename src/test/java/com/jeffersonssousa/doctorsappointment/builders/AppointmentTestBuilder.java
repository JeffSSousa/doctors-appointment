package com.jeffersonssousa.doctorsappointment.builders;

import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Patient;

import java.time.LocalDateTime;

public class AppointmentTestBuilder {

    private Long appoitmentId;
    private LocalDateTime appointmentDate = LocalDateTime.now().plusDays(1);
    private Integer durationInMinutes = 30;
    private boolean isReturn = false;

    private Doctor doctor;
    private Patient patient;

    private AppointmentTestBuilder() {}

    public static AppointmentTestBuilder anAppointment() {
        return new AppointmentTestBuilder();
    }

    public AppointmentTestBuilder withAppointmentId(Long id) {
        this.appoitmentId = id;
        return this;
    }

    public AppointmentTestBuilder withAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    public AppointmentTestBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
        return this;
    }

    public AppointmentTestBuilder withReturn(boolean isReturn) {
        this.isReturn = isReturn;
        return this;
    }

    public AppointmentTestBuilder withDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public AppointmentTestBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public Appointment build() {
        Appointment appointment = new Appointment();
        appointment.setAppoitmentId(appoitmentId);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDurationInMinutes(durationInMinutes);
        appointment.setReturn(isReturn);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        return appointment;
    }
}
