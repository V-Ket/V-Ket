package com.vket.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class MessageReq {
    Long chatroomId;
    String content; // 채팅 내용
    String userId; // 보내는 사람 Id

    @Builder
    public MessageReq (long chatroomId, String content, String userId) {
        super();
        this.chatroomId = chatroomId;
        this.content = content;
        this.userId = userId;
    }
}
