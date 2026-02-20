package com.skyz.api.core.config.exceptions.advices;

import com.skyz.api.core.config.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ApplicationAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String applicationAlreadyRegisteredHandler(ApplicationAlreadyRegisteredException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ApplicationAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String applicationAlreadyExistsHandler(ApplicationAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MandatorAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String mandatorAlreadyExistsHandler(MandatorAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ApplicationNotRegisteredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String applicationNotRegisteredHandler(ApplicationNotRegisteredException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MandatorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String mandatorNotFoundHandler(MandatorNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}
