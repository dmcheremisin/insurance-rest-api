package com.scale.global.insurance.app.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Customer not found with id = ";

    public CustomerNotFoundException(Integer id) {
        super(MESSAGE + id);
    }

    public CustomerNotFoundException(Integer id, Throwable cause) {
        super(MESSAGE + id, cause);
    }
}
