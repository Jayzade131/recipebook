package com.main.recipebook.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeBookException extends RuntimeException{

    private static final long serialVersionUID = 4806362458832660948L;

    private HttpStatus httpStatus;

    private String errorCode;

    private String errorMessage;

}
