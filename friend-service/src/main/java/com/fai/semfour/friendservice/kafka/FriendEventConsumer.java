package com.fai.semfour.friendservice.kafka;

import com.fai.semfour.friendservice.dto.FriendEvent;
import com.fai.semfour.friendservice.services.impl.FriendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendEventConsumer {

    private final FriendServiceImpl friendService;

    @KafkaListener(topics = "friend-events", groupId = "friend-group")
    public void consume(FriendEvent event) {
        log.info("Received FriendEvent from Kafka: {}", event);

        if ("ADD".equalsIgnoreCase(event.getEventType())) {
            log.info("Adding friend: userId={} friendId={}", event.getUserId(), event.getFriendId());
            friendService.addFriend(event.getUserId(), event.getFriendId());
        } else if ("REMOVE".equalsIgnoreCase(event.getEventType())) {
            log.info("Removing friend: userId={} friendId={}", event.getUserId(), event.getFriendId());
            friendService.removeFriend(event.getUserId(), event.getFriendId());
        } else {
            log.warn("Unknown eventType: {}", event.getEventType());
        }
    }
}
