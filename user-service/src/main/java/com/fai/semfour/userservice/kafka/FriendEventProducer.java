package com.fai.semfour.userservice.kafka;

import com.fai.semfour.userservice.dto.FriendEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendEventProducer {
    private final KafkaTemplate<String, FriendEvent> kafkaTemplate;
    private static final String TOPIC = "friend-events";

    public void sendMessage(FriendEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Sent Kafka message: " + event);
    }
}
