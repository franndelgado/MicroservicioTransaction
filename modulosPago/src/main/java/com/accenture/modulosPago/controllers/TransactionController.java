package com.accenture.modulosPago.controllers;

import com.accenture.modulosPago.dtos.TransactionDto;
import com.accenture.modulosPago.entities.Transaction;
import com.accenture.modulosPago.exceptions.TransactionServiceExceptions;
import com.accenture.modulosPago.repositories.TransactionRepository;
import com.accenture.modulosPago.service.InterfaceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired(required = true)
    @Qualifier("transactionServiceRestTemplate")
    private InterfaceTransactionService interfaceTransactionService;

    private static String DATE_FORMAT = "ddMMyyyy";
    @PostMapping("/createTransaction")
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDto transactionDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate paymentDate = LocalDate.parse(transactionDto.getTransactionDate(), formatter);

        if (transactionDto.getAmount().isNaN() || transactionDto.getDescription().trim().isEmpty() || transactionDto.getAccountNumberOrigin().trim().isEmpty()
                || transactionDto.getAccountNumberDestination().trim().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.NOT_ACCEPTABLE);
        }
        if (transactionDto.getAccountNumberDestination().equals(transactionDto.getAccountNumberOrigin())) {
            return new ResponseEntity<>("The accounts number are the same", HttpStatus.NOT_ACCEPTABLE);
        }
        if (transactionDto.getAmount() <= 0) {
            return new ResponseEntity<>("The amount cant be smaller than zero", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!Utils.verifyNumber(transactionDto.getAmount().toString())) {
            return new ResponseEntity<>("The amount must be a number", HttpStatus.NOT_ACCEPTABLE);
        }
        if (Utils.verifyTwoDecimals(transactionDto.getAmount())) {
            return new ResponseEntity<>("The amount must have two decimals", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!Utils.verifyNumber(transactionDto.getAccountNumberOrigin().toString())) {
            return new ResponseEntity<>("The account number of the sender must be a number", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!Utils.verifyNumber(transactionDto.getAccountNumberOrigin().toString())) {
            return new ResponseEntity<>("The account number of the reciver must be a number", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!interfaceTransactionService.AccountExist(transactionDto.getAccountNumberOrigin())) {
            return new ResponseEntity<>("The origin account no exist", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!interfaceTransactionService.AccountExist(transactionDto.getAccountNumberDestination())) {
            return new ResponseEntity<>("The destination account no exist", HttpStatus.NOT_ACCEPTABLE);
        }
        if (transactionDto.getAccountNumberOrigin().length() != 10) {
            return new ResponseEntity<>("The account number must be 10 digits", HttpStatus.NOT_ACCEPTABLE);
        }
        if (transactionDto.getAccountNumberDestination().length() != 10) {
            return new ResponseEntity<>("The account number must be 10 digits", HttpStatus.NOT_ACCEPTABLE);
        }
        if (paymentDate.isBefore(LocalDate.now())) {
            return new ResponseEntity<>("The date is wrong", HttpStatus.NOT_ACCEPTABLE);
        }
        Transaction transaction = interfaceTransactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    // || !(transactionDto.getTransactionDate().isEqual(LocalDateTime.now()))


    @GetMapping("/findDate")
    public ResponseEntity<Object> getTransactionBetweenDates(@RequestParam String fromDate, @RequestParam String toDate){
        try{
            List<TransactionDto> transactionList = interfaceTransactionService.getTransactionBetweenDates(fromDate, toDate);
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
        catch (TransactionServiceExceptions ex){
            ex.getMessage();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Throwable ex2){
            return new ResponseEntity<>(ex2.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}













//si la cuenta existe
//si el monto es igual o menor a el balance de la cuenta
/*if (!transaction.getAccountNumberDestination().isPresent()){
            return new ResponseEntity<>("The account destination isn't present", HttpStatus.NOT_ACCEPTABLE);
        }if (!transaction.getAccountNumberOrigin().isPresent()){
            return new ResponseEntity<>("The account origin isnt present", HttpStatus.NOT_ACCEPTABLE);
        }
*/