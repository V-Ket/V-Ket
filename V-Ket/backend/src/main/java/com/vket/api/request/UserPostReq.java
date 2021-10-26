package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPostRequest")
public class UserPostReq {

	@ApiModelProperty(name="유저 ID", example="your_id")
	String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String userPassword;
	@ApiModelProperty(name="유저 Nickname", example="your_nickname")
	String userNickname;
	@ApiModelProperty(name="유저 PhoneNumber", example="your_phonenumber")
	String userPhone;

}
