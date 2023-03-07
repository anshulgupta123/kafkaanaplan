package com.example.anaplanproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnaplanproducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnaplanproducerApplication.class, args);
    }


}
