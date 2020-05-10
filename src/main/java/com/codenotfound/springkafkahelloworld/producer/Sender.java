package com.codenotfound.springkafkahelloworld.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import example.avro.User;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private final KafkaTemplate<String, User> kafkaTemplate;

    private final String avroTopic;

    public Sender(KafkaTemplate<String, User> kafkaTemplate,
                  @Value("${kafka.topic.avro}") String avroTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.avroTopic = avroTopic;
    }

    public void send(User user) {
        LOGGER.info("sending user='{}'", user.toString());
        kafkaTemplate.send(avroTopic, user);
    }
}
