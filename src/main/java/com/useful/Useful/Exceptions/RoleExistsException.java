package com.useful.Useful.Exceptions;

public class RoleExistsException extends Exception {
    public RoleExistsException(String errorMessage) {
        super(errorMessage);
    }
}
