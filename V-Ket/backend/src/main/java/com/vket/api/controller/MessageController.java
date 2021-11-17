package com.vket.api.controller;

import com.vket.api.request.MessageReq;
import com.vket.api.service.ChatRoomService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final SimpMessagingTemplate template;

    ChatRoomController chatRoomController = new ChatRoomController();

    @MessageMapping("/")
    public void sendMessage(@Payload MessageReq message) {
        chatRoomController.addMessage(message);
        template.convertAndSend("/sub/" + message.getChatRoomId(), message);
    }

}
