package com.accenture.modulosPago.exceptions;

public class TransactionServiceExceptions extends Exception{

    private static String TransactionServiceMessage = "Error at the transaction microservice";


    public TransactionServiceExceptions() {
        this(TransactionServiceMessage);
    }

    public TransactionServiceExceptions(String message) {
        super(message);
    }

}
