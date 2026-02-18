package com.skyz.api.core.config.exceptions.advices;

import com.skyz.api.core.config.exceptions.ApplicationAlreadyRegisteredException;
import com.skyz.api.core.config.exceptions.ApplicationNotRegisteredException;
import com.skyz.api.core.config.exceptions.ClientNotFoundException;
import com.skyz.api.core.config.exceptions.MandatorNotFoundException;
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
