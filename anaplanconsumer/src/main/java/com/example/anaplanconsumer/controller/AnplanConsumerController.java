package com.example.anaplanconsumer.controller;

import com.example.anaplanconsumer.service.AnaplanConsumerService;
import com.example.anaplanconsumer.utility.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnplanConsumerController {

    Logger logger = LoggerFactory.getLogger(AnplanConsumerController.class);

    @Autowired
    AnaplanConsumerService anaplanConsumerService;

    @GetMapping(value = UrlConstants.GET_ALL_CLIENT_DETAILS)
    public ResponseEntity<Object> getAllClientDetails() {
        logger.info("Request for getAllClientDetails of AnplanConsumerController");
        return new ResponseEntity<>(anaplanConsumerService.getAllClientDetails(), HttpStatus.OK);
    }
}
