package com.accenture.modulosPago.service;

import com.accenture.modulosPago.dtos.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class TransferOperation {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.example}")
    private String queueExample;


private void sendMessage(TransactionDto transactionDto){
    jmsTemplate.send(queueExample, new MessageCreator() {
        @Override
        public Message createMessage(Session session) throws JMSException {
            ObjectMessage objectMessage = session.createObjectMessage(transactionDto);
            return objectMessage;
        }
    });
}

}
