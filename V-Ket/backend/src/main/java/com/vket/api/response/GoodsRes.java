package com.vket.api.response;

import com.vket.db.entity.Store;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("GoodsRes")
@Builder
public class GoodsRes {
    @ApiModelProperty(name="상품 아이디")
    private Long goodsId;

    @ApiModelProperty(name="상품명")
    private String goodsName;

    @ApiModelProperty(name="상품 가격")
    private Long goodsPrice;

    @ApiModelProperty(name="상품 이미지")
    private String goodsImg;

    @ApiModelProperty(name="상품 수량")
    private Long goodsQuantity;

    @ApiModelProperty(name="상품 설명")
    private String goodsContent;

    @ApiModelProperty(name="상품 담당 가게의 아이디")
    private Long storeId;

//    @ApiModelProperty(name="상품 담당 가게")
//    private Store store;
}
