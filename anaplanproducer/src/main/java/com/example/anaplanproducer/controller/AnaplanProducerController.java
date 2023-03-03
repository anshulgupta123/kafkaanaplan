package com.example.anaplanproducer.controller;

import com.example.anaplanproducer.dto.ClientRequestDto;
import com.example.anaplanproducer.service.ClientService;
import com.example.anaplanproducer.utility.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnaplanProducerController {

    Logger logger = LoggerFactory.getLogger(AnaplanProducerController.class);

    @Autowired
    ClientService clientService;

    @PostMapping(value = UrlConstants.SAVE_CLIENT_DATA)
    public ResponseEntity<Object> addClientData(@RequestBody ClientRequestDto clientRequestDto) {
       logger.info("Resuest for addClientData of AnaplanProducerController");
       return new ResponseEntity<>(clientService.addClient(clientRequestDto), HttpStatus.OK);
    }
}
