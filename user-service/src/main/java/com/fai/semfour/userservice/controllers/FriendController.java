package com.fai.semfour.userservice.controllers;

import com.fai.semfour.userservice.dto.FriendEvent;
import com.fai.semfour.userservice.kafka.FriendEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends-user")
@RequiredArgsConstructor
public class FriendController {
    private final FriendEventProducer friendEventProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendFriendEvent(@RequestBody FriendEvent friendEvent) {
        friendEventProducer.sendMessage(friendEvent);
        return ResponseEntity.ok("FriendEvent sent to Kafka: " + friendEvent);
    }
}