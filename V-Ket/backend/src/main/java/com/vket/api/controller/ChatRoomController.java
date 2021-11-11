package com.vket.api.controller;

import com.vket.api.request.ChatRoomReq;
import com.vket.api.request.MessageReq;
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

    static ArrayList<MessageReq> messageList = new ArrayList<MessageReq>();
    static void addMessage(MessageReq message) {
        messageList.add(message);
    }

    // 전체 채팅방 가져오기
    @GetMapping("")
    @ApiOperation(value = "전체 채팅방들 가져오기", notes = "")
    public ResponseEntity<List<ChatRoomRes>> findAllChatRooms() {
        List<ChatRoomRes> chatRoomList = chatRoomService.findAllChatRooms();
        return new ResponseEntity<List<ChatRoomRes>>(chatRoomList, HttpStatus.OK);
    }

    // 새 채팅방 만들기 (상점 주인과 대화 시작)
    @PostMapping("")
    @ApiOperation(value = "새 채팅방 만들기 (상점 주인과 대화 시작)", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addChatRoom(@RequestBody ChatRoomReq chatRoomReq) {
//        System.out.println(">>>>>> "+chatRoomReq.getChatRoomId() + " " + chatRoomReq.getSenderId() + " " + chatRoomReq.getReceiverId());
        if(chatRoomService.addChatRoom(chatRoomReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }

    // 사용자의 전체 채팅방 가져오기
    @GetMapping("/{userId}")
    @ApiOperation(value = "사용자가 속해 있는 채팅방들 가져오기", notes = "")
    public ResponseEntity<List<ChatRoomRes>> findChatRoomsByUserId(@PathVariable("userId") String userId) {
        List<ChatRoomRes> chatRoomListByUserId = chatRoomService.findChatRoomsByUserId(userId);

        return new ResponseEntity<List<ChatRoomRes>>(chatRoomListByUserId, HttpStatus.OK);
    }
    
    // 해당 채팅방 메시지들 가져오기
    @GetMapping("/message/{chatRoomId}")
    @ApiOperation(value = "채팅창 메시지 가져오기", notes = "")
    public ResponseEntity<ArrayList<MessageReq>> getMessageList(@PathVariable("chatRoomId") Long chatRoomId) {
        ArrayList<MessageReq> list = new ArrayList<MessageReq>();
        for(MessageReq messageReq: messageList) {
            if(messageReq.getChatRoomId() == chatRoomId) {
                list.add(messageReq);
            }
        }
        return new ResponseEntity<ArrayList<MessageReq>>(list, HttpStatus.OK);
    }
}
