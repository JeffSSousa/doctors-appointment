package com.jeffersonssousa.doctorsappointment.exception;

public class InvalidAppointmentDateException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InvalidAppointmentDateException(String msg){
        super(msg);
    }

}
