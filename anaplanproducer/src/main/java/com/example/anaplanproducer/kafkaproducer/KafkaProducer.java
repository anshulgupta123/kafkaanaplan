package com.example.anaplanproducer.kafkaproducer;

import com.example.anaplanproducer.dto.ClientKafkaDto;
import com.example.anaplanproducer.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, ClientKafkaDto> kafkaTemplate;

    public void sendMessage(ClientKafkaDto clientKafkaDto) {
        logger.info("Message to be send  is :{}", clientKafkaDto);
        Message<ClientKafkaDto> messageToSend = MessageBuilder.withPayload(clientKafkaDto)
                .setHeader(KafkaHeaders.TOPIC, Constants.Topic).build();
        kafkaTemplate.send(Constants.Topic, clientKafkaDto);
    }
}
