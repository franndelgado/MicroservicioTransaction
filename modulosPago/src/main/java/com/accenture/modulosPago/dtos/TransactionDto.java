package com.accenture.modulosPago.dtos;

import com.accenture.modulosPago.entities.PaymentType;
import com.accenture.modulosPago.entities.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDto implements Serializable {

    private Double amount;
    private String transactionDate;
    private TransactionType transactionType;
    private PaymentType paymentType;
    private String beneficiary;
    private String accountNumberOrigin; //sender
    private String accountNumberDestination;
    private String description;

    public TransactionDto() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getAccountNumberOrigin() {
        return accountNumberOrigin;
    }

    public void setAccountNumberOrigin(String accountNumberOrigin) {
        this.accountNumberOrigin = accountNumberOrigin;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "amount=" + amount +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionType=" + transactionType +
                ", paymentType=" + paymentType +
                ", beneficiary='" + beneficiary + '\'' +
                ", accountNumberOrigin='" + accountNumberOrigin + '\'' +
                ", accountNumberDestination='" + accountNumberDestination + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
