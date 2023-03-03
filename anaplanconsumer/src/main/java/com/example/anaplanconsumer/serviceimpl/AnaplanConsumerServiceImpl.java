package com.example.anaplanconsumer.serviceimpl;

import com.example.anaplanconsumer.dto.ClientDetailsResponseDto;
import com.example.anaplanconsumer.dto.Response;
import com.example.anaplanconsumer.exception.KafaConsumerException;
import com.example.anaplanconsumer.modal.ClientDetails;
import com.example.anaplanconsumer.repository.ClientDetailsRepository;
import com.example.anaplanconsumer.service.AnaplanConsumerService;
import com.example.anaplanconsumer.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnaplanConsumerServiceImpl implements AnaplanConsumerService {

    Logger logger = LoggerFactory.getLogger(AnaplanConsumerService.class);

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Autowired
    Environment env;

    @Override
    public Object getAllClientDetails() {
        logger.info("Inside getAllClientDetails of AnaplanConsumerServiceImpl");
        try {
            List<ClientDetailsResponseDto> clientDetailsResponseDtosList = new ArrayList<>();
            List<ClientDetails> clientDetailsList = clientDetailsRepository.findAll();
            for (ClientDetails clientDetails : clientDetailsList) {
                clientDetailsResponseDtosList.add(populateClientDetailsResponseDto(clientDetails));
            }
            return new Response<>(env.getProperty(Constants.SUCCESS_CODE), env.getProperty(Constants.CLIENT_DETAILS_FETCHED_SUCCESSFULLY), clientDetailsResponseDtosList);
        } catch (Exception e) {
            String errorMessage = null;
            if (e instanceof KafaConsumerException) {
                errorMessage = ((KafaConsumerException) e).getMessage();
            } else {
                errorMessage = MessageFormat.format("Exception caught in getAllClientDetails of AnaplanConsumerServiceImpl:{0}", e.getMessage());
            }
            logger.error(errorMessage);
            throw new KafaConsumerException(errorMessage);
        }

    }

    public ClientDetailsResponseDto populateClientDetailsResponseDto(ClientDetails clientDetails) throws Exception {
        logger.info("Inside populateClientDetailsResponseDto of AnaplanConsumerServiceImpl");
        ClientDetailsResponseDto clientDetailsResponseDto = new ClientDetailsResponseDto();
        clientDetailsResponseDto.setClientDetailsId(clientDetails.getClientDetailsId());
        clientDetailsResponseDto.setCountry(clientDetails.getCountry());
        clientDetailsResponseDto.setContactNumber(clientDetails.getContactNumber());
        return clientDetailsResponseDto;
    }
}
