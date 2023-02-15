package com.accenture.modulosPago.activeMQ;

import com.accenture.modulosPago.dtos.TransactionDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Autowired
    private Gson gson;

    @JmsListener(destination = "${queue.message.transaction}")

    public void receiveMessage(String message){
        TransactionDto transaction = gson.fromJson(message, TransactionDto.class);
        System.out.println(transaction);
        //System.out.println("USER: " + message);

    }
}
