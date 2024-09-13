package com.quick_bites.exceptions;


import com.quick_bites.dto.error_response.ErrorResponseMsg;
import com.quick_bites.mapper.AddressesMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //Creating a map to store error and it's value
        Map<String, String> validationErrors = new HashMap<>();


        //Getting the list of the errors
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();


        //Extracting each error key , value
        validationErrorList.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(MultipleRestaurantOrderException.class)
    public ResponseEntity<ErrorResponseMsg> handleMultipleRestaurantOrderException(MultipleRestaurantOrderException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleDishNotFoundException(DishNotFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleCartNotFoundException(CartNotFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleResourceNotFoundException(NoResourceFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(NoPaymentFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handlePaymentNotFoundException(NoPaymentFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(RazorPayException.class)
    public ResponseEntity<ErrorResponseMsg> handleRazorPayException(RazorPayException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(PlaceOrderException.class)
    public ResponseEntity<ErrorResponseMsg> handleOrderFailureException(PlaceOrderException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NoOrderHistoryFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleOrderHistoryNotFoundException(NoOrderHistoryFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(SlotNotAvailableOrExpireException.class)
    public ResponseEntity<ErrorResponseMsg> handleSlotServiceException(SlotNotAvailableOrExpireException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NoAddressFoundException.class)
    public ResponseEntity<ErrorResponseMsg> handleAddressNotFoundException(NoAddressFoundException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }




    @ExceptionHandler(SlotAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseMsg> handleSlotPresentException(SlotAlreadyExistsException ex, WebRequest request) {

        ErrorResponseMsg errorResponse = new ErrorResponseMsg(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }





}