package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("MyBuyListRes")
public class MyBuyListRes {

    @ApiModelProperty(name="판매자 Id")
    String sellerId;

    @ApiModelProperty(name="상품 이름")
    String goodsName;

    @ApiModelProperty(name="상품 사진")
    String goodsImg;

    @ApiModelProperty(name="상품 개당 가격")
    Long goodsPrice;

    @ApiModelProperty(name="구매 수량")
    Long goodsQuantity;

    @ApiModelProperty(name="구매 상태")
    Long purchaseStatus;

    @ApiModelProperty(name="deal id")
    Long dealId;

}
