package com.jeffersonssousa.doctorsappointment.builders;

import com.jeffersonssousa.doctorsappointment.entity.Appointment;
import com.jeffersonssousa.doctorsappointment.entity.Login;
import com.jeffersonssousa.doctorsappointment.entity.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;

public class PatientTestBuilder {

    private Long patientId;
    private String name = "Jefferson Silva de Sousa";
    private String cpf = "123.456.789-00";
    private LocalDate birthDate = LocalDate.of(1998,8,5);

    private Login login;

    private PatientTestBuilder(){}

    public static PatientTestBuilder aPatient(){
        return new PatientTestBuilder();
    }

    public PatientTestBuilder withPatientId(Long id){
        this.patientId = id;
        return this;
    }

    public PatientTestBuilder withName(String name){
        this.name = name;
        return this;
    }

    public PatientTestBuilder withCpf(String cpf){
        this.cpf = cpf;
        return this;
    }

    public PatientTestBuilder withBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
        return this;
    }

    public PatientTestBuilder withLogin(Login login){
        this.login = login;
        return this;
    }

    public Patient build(){
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setName(name);
        patient.setCpf(cpf);
        patient.setBirthDate(birthDate);
        patient.setLogin(login);
        return patient;
    }
}
