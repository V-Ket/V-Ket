package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DealAddReq")
@Data
public class DealAddReq {

    @ApiModelProperty(name="구매자 ID")
    String buyerId;

    @ApiModelProperty(name="판매자 ID")
    String sellerId;

    @ApiModelProperty(name="물건 이름")
    String goodsName;

    @ApiModelProperty(name="개당 가격")
    Long eachPrice;

    @ApiModelProperty(name="수량")
    Long quantity;

    @ApiModelProperty(name="물건 사진")
    String goodsImg;

}
