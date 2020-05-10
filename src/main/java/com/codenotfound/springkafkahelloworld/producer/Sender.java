package com.codenotfound.springkafkahelloworld.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private final KafkaTemplate<String, String> kafkaTemplate;


    public Sender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String payload) {
        LOGGER.info("sending payload='{}'", payload);
        kafkaTemplate.send("helloworld.t", payload);
    }
}
