package com.vket.api.controller;

import com.vket.api.request.MessageReq;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/")
    public void sendMessage(@Payload MessageReq message) {
        System.out.println(">>>>> 전달 주소 " + "/sub/" + message.getChatRoomId() + ", 전달 메시지 " + message);
        template.convertAndSend("/sub/" + message.getChatRoomId(), message);
    }
}
