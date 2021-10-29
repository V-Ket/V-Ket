package com.vket.api.controller;

import com.vket.api.response.ChatRoomRes;
import com.vket.api.service.ChatRoomService;
import com.vket.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Api(value = "채팅방 API", tags = {"ChatRooms"})
@RequestMapping("/chatRooms")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class ChatRoomController {
    @Autowired
    ChatRoomService chatRoomService;

    // 전체 채팅방 가져오기
    @GetMapping("")
    @ApiOperation(value = "전체 채팅방들 가져오기", notes = "")
    public ResponseEntity<List<ChatRoomRes>> findAllChatRooms() {
        List<ChatRoomRes> chatRoomList = chatRoomService.findAllChatRooms();

        return new ResponseEntity<List<ChatRoomRes>>(chatRoomList, HttpStatus.OK);
    }

    // 사용자의 전체 채팅방 가져오기
    @GetMapping("/{userId}")
    @ApiOperation(value = "사용자가 속해 있는 채팅방들 가져오기", notes = "")
    public ResponseEntity<List<ChatRoomRes>> findChatRoomsByUserId(@PathVariable("userId") String userId) {
        List<ChatRoomRes> chatRoomListByUserId = chatRoomService.findChatRoomsByUserId(userId);

        return new ResponseEntity<List<ChatRoomRes>>(chatRoomListByUserId, HttpStatus.OK);
    }
}
