package com.vket.api.service;

import com.vket.api.response.ChatRoomRes;
import com.vket.db.entity.ChatRoom;
import com.vket.db.repository.ChatRootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRootRepository chatRootRepository;

    @Override
    public List<ChatRoomRes> findAllChatRooms() {
        List<ChatRoomRes> chatRoomResList =  new ArrayList<ChatRoomRes>();
        List<ChatRoom> chatRooms = chatRootRepository.findAll();

        for(ChatRoom chatRoom: chatRooms) {
            chatRoomResList.add(ChatRoomRes.builder()
                    .chatRoomId(chatRoom.getChatRoomId())
                    .senderId(chatRoom.getSenderId())
                    .receiverId(chatRoom.getReceiverId())
                    .build());
        }
        return chatRoomResList;
    }

    @Override
    public List<ChatRoomRes> findChatRoomsByUserId(String userId) {
        List<ChatRoomRes> chatRoomResList =  new ArrayList<ChatRoomRes>();
        List<ChatRoom> chatRooms = chatRootRepository.findAll();

        for(ChatRoom chatRoom: chatRooms) {
            if(chatRoom.getReceiverId().equals(userId) || chatRoom.getSenderId().equals(userId)) {
                chatRoomResList.add(ChatRoomRes.builder()
                        .chatRoomId(chatRoom.getChatRoomId())
                        .senderId(chatRoom.getSenderId())
                        .receiverId(chatRoom.getReceiverId())
                        .build());
            }
        }

        return chatRoomResList;
    }
}
