package com.accenture.modulosPago.controllers;

import com.accenture.modulosPago.dtos.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@RestController
@RequestMapping("/api/v1")
public class SendController {
    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/send/")
    public void send(){
        jmsTemplate.send("${queue.message.transaction}", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage object = session.createObjectMessage(new TransactionDto());
                return object;
            }
        });
    }
}
