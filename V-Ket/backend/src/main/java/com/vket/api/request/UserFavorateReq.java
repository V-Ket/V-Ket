package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFavorateReq")
public class UserFavorateReq {

    @ApiModelProperty(name = "유저 Id", example = "user_Seq")
    String userId;
    @ApiModelProperty(name = "상점 ID", example = "store_id")
    Long storeId;
}
