package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostRequest")
public class UserLoginPostReq {

    @ApiModelProperty(name="유저 ID", example="your_id")
    String userId;
    @ApiModelProperty(name="유저 Password", example="your_password")
    String userPassword;

}
