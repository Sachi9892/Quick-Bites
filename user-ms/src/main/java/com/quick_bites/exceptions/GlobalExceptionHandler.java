package com.quick_bites.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {

        //Creating a map to store error and it's value
        Map<String, String> validationErrors = new HashMap<>();

        //Getting the list of the errors
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        //Extracting each error key , value
        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


    //Handle any exception
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
//                                                                  WebRequest webRequest) {
//        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
//                webRequest.getDescription(false),
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                exception.getMessage(),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//    // Handle  ResourceAlreadyPresent
//    @ExceptionHandler(ResourceAlreadyPresent.class)
//    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyPresentExp( ResourceAlreadyPresent ex , WebRequest req ) {
//
//        ErrorResponseDto response = new ErrorResponseDto();
//
//        response.setApiPath(req.getDescription(false));
//        response.setStatus(HttpStatus.BAD_REQUEST);
//        response.setErrorMsg(ex.getMessage());
//        response.setTime(LocalDateTime.now());
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//
//    }
//
//
//    // Handle ResourceNotFoundException
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundExp( ResourceAlreadyPresent ex , WebRequest req ) {
//
//        ErrorResponseDto response = new ErrorResponseDto();
//
//        response.setApiPath(req.getDescription(false));
//        response.setStatus(HttpStatus.BAD_REQUEST);
//        response.setErrorMsg(ex.getMessage());
//        response.setTime(LocalDateTime.now());
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//
//    }



}