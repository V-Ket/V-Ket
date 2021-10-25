package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("UserLoginPostResponse")
public class UserLoginPostRes {
	@ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;

	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;

	@ApiModelProperty(name="응답 코드", example = "200")
	Integer statusCode = null;

	@ApiModelProperty(name="유저 seq", example = "1")
	Long userSeq;

	@ApiModelProperty(name="유저 Id", example = "your_id")
	String userId;

	@ApiModelProperty(name="유저 Phone", example = "01012341234")
	String userPhone;

	@ApiModelProperty(name="유저 Nickname", example = "your_nickname")
	String userNickname;

	@ApiModelProperty(name="유저 Character", example = "your_character")
	Long userCharacter = 0L;



//	@ApiModelProperty(name="유저 Credit", example = "your_credit")
//	Long userCredit;
}
