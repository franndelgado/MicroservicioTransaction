package com.accenture.modulosPago.service;

import com.accenture.modulosPago.dtos.TransactionDto;
import com.accenture.modulosPago.entities.Transaction;
import com.accenture.modulosPago.exceptions.TransactionServiceExceptions;

import java.util.List;

public interface InterfaceTransactionService {

   public Boolean AccountExist(String accountNumber);

   public Transaction createTransaction(TransactionDto transactionDto);

   public List<Transaction> findAll();
   public List<TransactionDto> getTransactionBetweenDates(String fromDateStr, String toDateStr) throws TransactionServiceExceptions;

}
