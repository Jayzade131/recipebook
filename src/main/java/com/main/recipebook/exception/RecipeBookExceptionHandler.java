package com.main.recipebook.exception;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RecipeBookExceptionHandler {

    @ExceptionHandler(RecipeBookException.class)
    public ResponseEntity<ErrorResponse> handleException(RecipeBookException ex)
    {
        log.info("Exception is --> "+ex.getErrorMessage());

      ErrorResponse errorResponse=  ErrorResponse.builder().errorCode(ex.getErrorCode()).errorMessage(ex.getErrorMessage()).build();

      log.info("Error Response -->"+errorResponse);

      return new ResponseEntity<>(errorResponse ,ex.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex)
    {
        log.info("Exception is --> "+ex.getMessage());

        ErrorResponse errorResponse=  ErrorResponse.builder().errorCode(ErrorCodeEnum.USER_NOT_FOUND.getErrorCode()).errorMessage(ErrorCodeEnum.USER_NOT_FOUND.getErrorMessage()).build();

        log.info("Error Response -->"+errorResponse);

        return new ResponseEntity<>(errorResponse ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenricException(Exception ex)
    {
        log.info("Genric Exception -> "+ex.getMessage());
        ErrorResponse errorResponse=  ErrorResponse.builder().errorCode(ErrorCodeEnum.GENERIC_EXCEPTION.getErrorCode())
                .errorMessage(ErrorCodeEnum.GENERIC_EXCEPTION.getErrorMessage()).build();

        log.info("Genric Error Response -->"+errorResponse);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        log.info("Validation Exception -> "+ex.getMessage());
        var collect = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        ErrorResponse errorResponse=  ErrorResponse.builder().errorCode(ErrorCodeEnum.VALIDATION_EXCEPTION.getErrorCode()).errorMessage(collect).build();

        log.info(" Error Response -->"+errorResponse);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }



}
