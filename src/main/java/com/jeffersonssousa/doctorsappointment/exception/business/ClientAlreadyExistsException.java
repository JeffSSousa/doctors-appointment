package com.jeffersonssousa.doctorsappointment.exception.business;

public class ClientAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
