package com.skyz.api.core.config.exceptions.advices;

import com.skyz.api.core.config.exceptions.ApplicationAlreadyRegisteredException;
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
}
