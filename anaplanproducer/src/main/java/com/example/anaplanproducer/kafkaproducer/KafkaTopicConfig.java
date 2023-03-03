package com.example.anaplanproducer.kafkaproducer;

import com.example.anaplanproducer.utility.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic javaguidesTopic() {
        return TopicBuilder.name(Constants.Topic).partitions(2).replicas(1).build();
    }
}
