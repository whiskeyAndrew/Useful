package com.useful.Useful.Exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
