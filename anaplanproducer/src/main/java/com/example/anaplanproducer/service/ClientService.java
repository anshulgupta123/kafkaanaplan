package com.example.anaplanproducer.service;

import com.example.anaplanproducer.dto.ClientRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
   public  Object addClient(ClientRequestDto clientRequestDto);
}
