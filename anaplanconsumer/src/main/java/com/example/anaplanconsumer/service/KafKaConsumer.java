package com.example.anaplanconsumer.service;

import com.example.anaplanconsumer.dto.ClientKafkaDto;
import com.example.anaplanconsumer.modal.ClientDetails;
import com.example.anaplanconsumer.repository.ClientDetailsRepository;
import com.example.anaplanconsumer.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafKaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafKaConsumer.class);

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @KafkaListener(topics = Constants.Topic, groupId = Constants.GROUP_ID)
    public void consume(String message) {
        logger.info("Inside consume of KafkaConsumer");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClientKafkaDto clientKafkaDto = objectMapper.readValue(message, ClientKafkaDto.class);
            clientDetailsRepository.save(getPopulatedClientDetails(clientKafkaDto));
            logger.info("Client Details saved successfully");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    public ClientDetails getPopulatedClientDetails(ClientKafkaDto clientKafkaDto) throws Exception {
        logger.info("Inside getPopulatedClientDetails of KafkaConsumer");
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setCountry(clientKafkaDto.getCountry());
        clientDetails.setContactNumber(clientKafkaDto.getContactNumber());
        return clientDetails;
    }
}
