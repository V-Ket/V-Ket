package com.vket.api.service;

import com.vket.api.request.ChatRoomReq;
import com.vket.api.response.ChatRoomRes;
import com.vket.db.entity.ChatRoom;
import com.vket.db.repository.ChatRootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRootRepository chatRootRepository;

    @Override
    public boolean addChatRoom(ChatRoomReq chatRoomReq) {

        Optional<ChatRoom> chatRoom = chatRootRepository.findBySenderIdAndReceiverId(chatRoomReq.getSenderId(), chatRoomReq.getReceiverId());

        // 이미 방이 존제하면
        if(chatRoom.isPresent()){
            return false;
        }

        chatRootRepository.save(ChatRoom.builder()
//            .chatRoomId(chatRoomReq.getChatRoomId())
            .senderId(chatRoomReq.getSenderId())
            .receiverId(chatRoomReq.getReceiverId())
            .build());
        return true;
    }

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
