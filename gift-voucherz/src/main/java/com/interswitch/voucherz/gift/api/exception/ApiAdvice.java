package com.interswitch.voucherz.gift.api.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.interswitch.voucherz.gift.api.exception.RequestException;
import com.interswitch.voucherz.gift.api.model.exception.Error;
import com.interswitch.voucherz.gift.api.model.exception.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiAdvice {
    @ExceptionHandler(RequestException.class)
    public void handleCustomException(HttpServletResponse response, RequestException e) throws IOException {
        log.error("ERROR", e);
        response.sendError(e.getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse response, AccessDeniedException e) throws IOException {
        log.error("ERROR", e);
        response.sendError(HttpStatus.FORBIDDEN.value(), withMessage(e));
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse response, Exception e) throws IOException {
        log.error("ERROR", e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), withMessage(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationException(HttpServletResponse response, MethodArgumentNotValidException e)throws IOException {
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        response.sendError(HttpStatus.BAD_REQUEST.value(), withMessage(e));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpServletResponse response, HttpMessageNotReadableException e) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), withMessage(e));
    }

    private String withMessage(Throwable e){
        String message = null;
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
            if (e.getCause() instanceof JsonMappingException) {
                String[] arr = message.split("\\(");
                if (arr.length > 0) {
                    String temp = arr[0];
                    String[] arr2 = message.split("\\[");
                    if (arr2.length > 1) {
                        message = temp + " (field: [" + arr2[1];
                    } else {
                        message = temp;
                    }
                }
            }

            if (e.getCause() instanceof JsonParseException) {
                String[] arr = message.split("at");
                if (arr.length > 0) {
                    String temp = arr[0];
                    JsonParseException jpe = (JsonParseException) e.getCause();
                    message = temp + " [line: " + jpe.getLocation().getLineNr() + ", column: " + jpe.getLocation().getColumnNr() + "]";
                }
            }

        }
        return message;
    }
}

