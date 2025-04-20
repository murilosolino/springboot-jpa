package com.personalproject.springJpaHibernate.resources.exceptions;

import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.NullDataObjectException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFundException.class)
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandartError> dataBase(DataBaseException e, HttpServletRequest request){
        String error = "DataBase Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NullDataObjectException.class)
    public ResponseEntity<StandartError> dataObject(NullDataObjectException e, HttpServletRequest request){
        String error = "Data Object Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
