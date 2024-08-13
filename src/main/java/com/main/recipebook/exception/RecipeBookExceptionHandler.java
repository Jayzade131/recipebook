package com.main.recipebook.exception;

import com.main.recipebook.constant.ErrorCodeEuum;
import com.main.recipebook.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenricException(Exception ex)
    {
        log.info("Genric Exception -> "+ex.getMessage());
        ErrorResponse errorResponse=  ErrorResponse.builder().errorCode(ErrorCodeEuum.GENERIC_EXCEPTION.getErrorCode())
                .errorMessage(ErrorCodeEuum.GENERIC_EXCEPTION.getErrorMessage()).build();

        log.info("Genric Error Response -->"+errorResponse);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
