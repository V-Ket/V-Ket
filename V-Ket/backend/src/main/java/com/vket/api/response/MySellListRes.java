package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("MySellListRes")
public class MySellListRes {

    @ApiModelProperty(name="구매자 Id")
    String buyerId;

    @ApiModelProperty(name="상품 이름")
    String goodsName;

    @ApiModelProperty(name="상품 사진")
    String goodsImg;

    @ApiModelProperty(name="상품 개당 가격")
    Long goodsPrice;

    @ApiModelProperty(name="판매 수량")
    Long goodsQuantity;

    @ApiModelProperty(name="판매 상태")
    Long purchaseStatus;

    @ApiModelProperty(name="deal id")
    Long dealId;
}
