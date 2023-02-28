package com.accenture.modulosPago.entities;

import com.accenture.modulosPago.dtos.TransactionDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column
    private Double amount;
    @Column
    private String transactionDate;
    @Column
    private TransactionType transactionType;
    @Column
    private PaymentType paymentType;
    @Column
    private String beneficiary;
    @Column
    private String accountNumberOrigin; //sender
    @Column
    private String accountNumberDestination;
    @Column
    private String description;

    public Transaction() {
    }

    public Transaction(TransactionDto transactionDto) {
        this.amount = transactionDto.getAmount();
        this.transactionDate = transactionDto.getTransactionDate();
        this.transactionType = transactionDto.getTransactionType();
        this.paymentType = transactionDto.getPaymentType();
        this.beneficiary = transactionDto.getBeneficiary();
        this.accountNumberOrigin = transactionDto.getAccountNumberOrigin();
        this.accountNumberDestination = transactionDto.getAccountNumberDestination();
        this.description = transactionDto.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
