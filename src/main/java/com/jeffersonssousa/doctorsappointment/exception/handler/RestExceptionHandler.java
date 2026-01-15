package com.jeffersonssousa.doctorsappointment.exception.handler;

import com.jeffersonssousa.doctorsappointment.exception.business.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound (UserNotFoundException e, HttpServletRequest request){
        String error = "Entidade não encontrada";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),
                                                status.value(),
                                                error,
                                                e.getMessage(),
                                                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException (IllegalArgumentException e, HttpServletRequest request){
        String error = "Argumento ilegal";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                                                status.value(),
                                                error,
                                                e.getMessage(),
                                                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidAppointmentDateException.class)
    public ResponseEntity<StandardError> invalidAppointmentDate (InvalidAppointmentDateException e, HttpServletRequest request){
        String error = "Data está errada";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<StandardError> appointmentConflict (AppointmentConflictException e, HttpServletRequest request){
        String error = "Violação/Conflito com agendamento";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<StandardError> clientAlreadyExists (ClientAlreadyExistsException e, HttpServletRequest request){
        String error = "Cliente já existe";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DoctorAlreadyLinkedException.class)
    public ResponseEntity<StandardError> doctorAlreadyLinked (DoctorAlreadyLinkedException e, HttpServletRequest request){
        String error = "Doutor já vinculado";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PatientAlreadyLinkedException.class)
    public ResponseEntity<StandardError> patientAlreadyLinked (PatientAlreadyLinkedException e, HttpServletRequest request){
        String error = "Paciente já vinculado";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<StandardError> emailAlreadyInUse (EmailAlreadyInUseException e, HttpServletRequest request){
        String error = "E-mail já vinculado a um usuario";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExistsException (UserAlreadyExistsException e, HttpServletRequest request){
        String error = "Já existe um usuario com esse username";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
