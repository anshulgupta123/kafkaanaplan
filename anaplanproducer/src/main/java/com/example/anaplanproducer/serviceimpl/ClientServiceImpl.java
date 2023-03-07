package com.example.anaplanproducer.serviceimpl;

import com.example.anaplanproducer.dto.ClientKafkaDto;
import com.example.anaplanproducer.dto.ClientRequestDto;
import com.example.anaplanproducer.dto.Response;
import com.example.anaplanproducer.exception.AnaplanProducerException;
import com.example.anaplanproducer.kafkaproducer.KafkaProducer;
import com.example.anaplanproducer.modal.Client;
import com.example.anaplanproducer.repository.ClientRepository;
import com.example.anaplanproducer.service.ClientService;
import com.example.anaplanproducer.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ClientServiceImpl implements ClientService {

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    Environment env;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Object addClient(ClientRequestDto clientRequestDto) {
        logger.info("Inside addClient of ClientServiceImpl");
        try {
            validateAddClientData(clientRequestDto);
            sendDataToAnaplanConsumer(clientRequestDto);
            Client savedCilentData = clientRepository.save(populateClientFromRequestData(clientRequestDto));
            logger.info("Data saved successfully in client");
            return new Response<>(env.getProperty(Constants.SUCCESS_CODE), env.getProperty(Constants.CLIENT_DATA_SAVED_SUCCESSFULLY), savedCilentData);
        } catch (Exception e) {
            String errorMessage = null;
            if (e instanceof AnaplanProducerException) {
                errorMessage = ((AnaplanProducerException) e).getMessage();
            } else {
                errorMessage = MessageFormat.format("Exception caught in addClient of ClientServiceImpl:{0}", e.getMessage());
            }
            logger.error(errorMessage);
            throw new AnaplanProducerException(errorMessage);
        }
    }

    public void sendDataToAnaplanConsumer(ClientRequestDto clientRequestDto) throws Exception {
        logger.info("Inside sendDataToAnaplanConsumer of ClientServiceImpl");
        kafkaProducer.sendMessage(populateClientKafkaDto(clientRequestDto));
        logger.info("Data sent to consumer service");
    }

    public ClientKafkaDto populateClientKafkaDto(ClientRequestDto clientRequestDto) throws Exception {
        logger.info("Inside populateClientKafkaDto of ClientServiceImpl");
        ClientKafkaDto clientKafkaDto = new ClientKafkaDto();
        clientKafkaDto.setCountry(clientRequestDto.getCountry());
        clientKafkaDto.setContactNumber(clientRequestDto.getContactNumber());
        return clientKafkaDto;
    }

    public Client populateClientFromRequestData(ClientRequestDto clientRequestDto) throws Exception {
        logger.info("Inside populateClientFromRequestData of ClientServiceImpl");
        Client client = new Client();
        client.setClientName(clientRequestDto.getClientName());
        return client;
    }

    public void validateAddClientData(ClientRequestDto clientRequestDto) throws Exception {
        if (clientRequestDto.getClientName() == null || clientRequestDto.getClientName().isEmpty() || clientRequestDto.getCountry() == null ||
                clientRequestDto.getCountry().isEmpty() || clientRequestDto.getContactNumber() == null || clientRequestDto.getContactNumber().isEmpty()) {
            throw new AnaplanProducerException(env.getProperty(Constants.INVALID_REQUEST_DATA));
        }
    }

}
