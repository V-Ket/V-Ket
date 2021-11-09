package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("GoodsUpdateReq")
@Data
public class GoodsUpdateReq {
    @ApiModelProperty(name="상품 아이디")
    private Long goodsId;

    @ApiModelProperty(name="상품명")
    private String goodsName;

    @ApiModelProperty(name="상품 가격")
    private Long goodsPrice;

    @ApiModelProperty(name="상품 이미지")
    private MultipartFile goodsImg;

    @ApiModelProperty(name="상품 수량")
    private Long goodsQuantity;

    @ApiModelProperty(name="상품 설명")
    private String goodsContent;

    @ApiModelProperty(name="상품 담당 가게의 아이디")
    private Long storeId;
}
