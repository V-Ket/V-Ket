package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SessionPostReq")
@Data
public class SessionPostReq {

    @ApiModelProperty(name="구매자 id")
    String buyerId;

    @ApiModelProperty(name="판매자 id")
    String sellerId;
}
