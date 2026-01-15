package com.jeffersonssousa.doctorsappointment.exception.business;

public class DoctorAlreadyLinkedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DoctorAlreadyLinkedException(String message) {
        super(message);
    }
}
