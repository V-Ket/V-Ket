package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CreditAddReq")
@Data
public class CreditAddReq {

    @ApiModelProperty(name="이용자 ID")
    String userId;

    @ApiModelProperty(name="충전할 credit")
    Long credit;
}
