package com.accenture.modulosPago.exceptions;

public class InvalidLengthException extends TransactionServiceExceptions{

    private static String InvalidLength = "The date must have 8 digits";

    public InvalidLengthException() {
        super(InvalidLength);
    }
}
