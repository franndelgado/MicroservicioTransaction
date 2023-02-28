package com.accenture.modulosPago.service;

import com.accenture.modulosPago.dtos.TransactionDto;
import com.accenture.modulosPago.entities.Transaction;
import com.accenture.modulosPago.exceptions.InvalidLengthException;
import com.accenture.modulosPago.exceptions.TransactionServiceExceptions;
import com.accenture.modulosPago.models.Account;
import com.accenture.modulosPago.models.User;
import com.accenture.modulosPago.repositories.TransactionRepository;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("transactionServiceRestTemplate")
public class TransactionService implements InterfaceTransactionService{
    @Autowired
    private RestTemplate clientRest;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Gson gson;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queue.message.transaction}")
    private String queueMessageTransaction;

    @Override
    public Boolean AccountExist(String accountNumber){
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("number", accountNumber);
        Account account = clientRest.getForObject("http://accountMicroService/api/accounts/list/number/{number}", Account.class, pathVariables);
        if(account != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction(transactionDto);
        clientRest.postForEntity("http://accountMicroService/api/accounts/updateBalance", transactionDto, Account.class);
        sendMessage(transactionDto);
        return transactionRepository.save(transaction);
    }

    private void sendMessage(TransactionDto transactionDto) {
        jmsTemplate.convertAndSend(queueMessageTransaction, gson.toJson(transactionDto));
    }

    @Override
    public List<Transaction> findAll() {
        return (List<Transaction>) transactionRepository.findAll();
    }


    public List<TransactionDto> getTransactionBetweenDates(String fromDateStr, String toDateStr) throws TransactionServiceExceptions {

        if(fromDateStr.length() != 8 || toDateStr.length() != 8 ){
                throw new InvalidLengthException();
        }
        try{
            List<Transaction> list = (List<Transaction>) transactionRepository.findAll();
            return list.stream()
                    .map(e -> mapper.map(e, TransactionDto.class))
                    .filter(e -> isValidDate(e.getTransactionDate().toString(), fromDateStr, toDateStr))
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw t;
        }
    }

    private Boolean isValidDate(String dateTransaction, String fromDateStr, String toDateStr) {
        System.out.println(dateTransaction);
        System.out.println(fromDateStr);
        System.out.println(toDateStr);
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
            LocalDate toDate = LocalDate.parse(toDateStr, formatter);
            System.out.println(dateTransaction);
            System.out.println(fromDateStr);
            System.out.println(toDateStr);
            if((LocalDate.parse(dateTransaction, formatter)).equals(fromDate) || (LocalDate.parse(dateTransaction, formatter)).equals(toDate)) {
                return Boolean.TRUE;
            }
            if((LocalDate.parse(dateTransaction, formatter)).isAfter(fromDate) && (LocalDate.parse(dateTransaction, formatter)).isBefore(toDate)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Throwable t) {
            throw t;
        }
    }
}
