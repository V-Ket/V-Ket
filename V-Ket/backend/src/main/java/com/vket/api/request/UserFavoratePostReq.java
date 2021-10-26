package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFavoratePostReq")
public class UserFavoratePostReq {

    @ApiModelProperty(name = "유저 Seq", example = "user_Seq")
    Long userSeq;
    @ApiModelProperty(name = "상점 ID", example = "store_id")
    Long storeId;
}
