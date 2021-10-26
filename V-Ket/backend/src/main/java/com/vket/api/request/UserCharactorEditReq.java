package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserCharactorEditReq")
public class UserCharactorEditReq {

    @ApiModelProperty(name = "유저 ID", example = "your_id")
    String userId;
    @ApiModelProperty(name = "유저 Charactor", example = "your_charactor")
    Long userCharactor;

}
