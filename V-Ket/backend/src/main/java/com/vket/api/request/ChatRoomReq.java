package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel("ChatRoomReq")
public class ChatRoomReq {

    @ApiModelProperty(name="송신자 Id")
    private String senderId;

    @ApiModelProperty(name="수신자 Id")
    private String receiverId;
}
