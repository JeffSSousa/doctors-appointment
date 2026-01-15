package com.jeffersonssousa.doctorsappointment.exception;

public class PatientAlreadyLinkedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PatientAlreadyLinkedException(String message) {
        super(message);
    }
}
