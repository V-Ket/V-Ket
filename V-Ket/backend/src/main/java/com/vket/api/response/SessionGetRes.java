package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SessionGetRes")
@Builder
public class SessionGetRes {
    @ApiModelProperty(name="session 이름")
    String sessionName;

    @ApiModelProperty(name="판매자 id")
    String sellerId;

    @ApiModelProperty(name="구매자 id")
    String buyerId;

    @ApiModelProperty(name="상점 id")
    Long storeId;
}
