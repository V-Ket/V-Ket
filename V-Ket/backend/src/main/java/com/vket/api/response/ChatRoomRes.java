package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ChatRoomRes")
@Builder
public class ChatRoomRes {
    @ApiModelProperty(name="채팅방 번호")
    private long chatRoomId;

    @ApiModelProperty(name="송신자 Id")
    private String senderId;

    @ApiModelProperty(name="수신자 Id")
    private String receiverId;
}
