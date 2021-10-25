package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserNicknameEditRequest")
public class UserNicknameEditReq {

    @ApiModelProperty(name="유저 ID", example="pg12@profileglance.com")
    String userId;
    @ApiModelProperty(name="유저 Nickname", example="your_nickname")
    String userNickname;
}
