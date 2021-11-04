package com.vket.api.service;

import com.vket.api.request.ChatRoomReq;
import com.vket.api.response.ChatRoomRes;

import java.util.List;

public interface ChatRoomService {
    boolean addChatRoom(ChatRoomReq chatRoomReq);

    List<ChatRoomRes> findAllChatRooms();

    List<ChatRoomRes> findChatRoomsByUserId(String userId);
}
