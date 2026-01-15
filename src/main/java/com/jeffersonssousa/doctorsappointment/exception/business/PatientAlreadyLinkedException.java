package com.jeffersonssousa.doctorsappointment.exception.business;

public class PatientAlreadyLinkedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PatientAlreadyLinkedException(String message) {
        super(message);
    }
}
