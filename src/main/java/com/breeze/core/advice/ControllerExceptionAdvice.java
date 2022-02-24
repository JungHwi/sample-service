package com.breeze.core.advice;


import com.breeze.core.exception.SampleException;
import com.breeze.core.exception.UnauthorizedException;
import com.breeze.core.wrapper.ResultResponse;
import lombok.extern.log4j.Log4j2;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class ControllerExceptionAdvice {

    private Environment environment;

    public ControllerExceptionAdvice(Environment environment){
        this.environment = environment;
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultResponse handleException(UnauthorizedException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.UNAUTHORIZED);
        resultResponse.setCode(40100);
        resultResponse.setMessage(e.getMessage());
        return resultResponse;
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultResponse handleException(AccessDeniedException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.FORBIDDEN);
        resultResponse.setCode(40300);
        resultResponse.setMessage(e.getMessage());
        return resultResponse;
    }

    @ExceptionHandler(SampleException.class)
    public ResponseEntity<ResultResponse> handleException(SampleException e) {
        ResultResponse resultResponse = new ResultResponse(e.getHttpStatus());
        resultResponse.setMessage(e.getMessage());
        resultResponse.setCode(e.getCode());
        return new ResponseEntity<>(resultResponse, e.getHttpStatus());
    }

    @ExceptionHandler({ValidationException.class, TypeMismatchException.class,
            ServletException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultResponse handleValidateException(Exception e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST);
        resultResponse.setMessage(e.getMessage());
        resultResponse.setCode(40099);
        return resultResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST);
        resultResponse.setCode(40099);

        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> String.format("%s : %s", fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        resultResponse.setMessage(StringUtils.join(errorMessages,  ','));

        log.warn("");
        return resultResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse handleException(Exception e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        resultResponse.setMessage(isProduction() ? "Server Error" : e.getMessage());
        for (StackTraceElement stack : e.getStackTrace()) {
            log.warn("#### {}", stack);
        }
        return resultResponse;
    }

    private boolean isProduction() {
        if (environment == null) {
            return false;
        }
        return Arrays.asList(environment.getActiveProfiles()).contains("production");
    }
}
