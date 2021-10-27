package com.vket.api.service;

import com.vket.api.response.ChatRoomRes;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoomRes> findAllChatRooms();

    List<ChatRoomRes> findChatRoomsByUserId(String userId);
}
