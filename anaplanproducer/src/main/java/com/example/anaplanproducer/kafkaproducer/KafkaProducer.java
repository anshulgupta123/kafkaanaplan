package com.example.anaplanproducer.kafkaproducer;

import com.example.anaplanproducer.dto.ClientKafkaDto;
import com.example.anaplanproducer.exception.AnaplanProducerException;
import com.example.anaplanproducer.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(org.apache.kafka.clients.producer.KafkaProducer.class);


    @Autowired
    private KafkaTemplate<String, ClientKafkaDto> kafkaTemplate;

    @Autowired
    Environment environment;

    public void sendMessage(ClientKafkaDto clientKafkaDto) {
        logger.info("Message to be send  is :{}", clientKafkaDto);
        Message<ClientKafkaDto> messageToSend = MessageBuilder.withPayload(clientKafkaDto)
                .setHeader(KafkaHeaders.TOPIC, Constants.Topic).build();
        ListenableFuture<SendResult<String, ClientKafkaDto>> listenableFuture = kafkaTemplate.send(Constants.Topic, clientKafkaDto);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, ClientKafkaDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Some error ocuured in anpalan consumer :{}", ex.getMessage());
                throw new AnaplanProducerException(environment.getProperty(Constants.ANAPLAN_CONSUMER_ERROR));
            }

            @Override
            public void onSuccess(SendResult<String, ClientKafkaDto> result) {
                logger.info("Message sent successfully");
            }
        });
    }
}
