package com.jeffersonssousa.doctorsappointment.builders;

import com.jeffersonssousa.doctorsappointment.entity.Doctor;
import com.jeffersonssousa.doctorsappointment.entity.Login;

public class DoctorTestBuilder {

    private Long doctorId;
    private String name = "Dr. Jefferson Silva";
    private String phone = "(11) 99999-9999";
    private String crm = "CRM-SP-123456";
    private String specialty = "Cardiologia";

    private Login login;

    private DoctorTestBuilder() {}

    public static DoctorTestBuilder aDoctor() {
        return new DoctorTestBuilder();
    }

    public DoctorTestBuilder withDoctorId(Long id) {
        this.doctorId = id;
        return this;
    }

    public DoctorTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DoctorTestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public DoctorTestBuilder withCrm(String crm) {
        this.crm = crm;
        return this;
    }

    public DoctorTestBuilder withSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public DoctorTestBuilder withLogin(Login login) {
        this.login = login;
        return this;
    }

    public Doctor build() {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setName(name);
        doctor.setPhone(phone);
        doctor.setCrm(crm);
        doctor.setSpecialty(specialty);
        doctor.setLogin(login);
        return doctor;
    }
}
