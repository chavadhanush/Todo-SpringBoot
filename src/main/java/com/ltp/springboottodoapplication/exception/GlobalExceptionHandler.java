package com.ltp.springboottodoapplication.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationErrors(Model model, MethodArgumentNotValidException ex) {
        // Extract validation errors and add them to the model
        model.addAttribute("validationErrors", ex.getBindingResult().getAllErrors());
        // Return the view name with validation errors
        return "error"; // Assuming you have an error.html template
    }
}
