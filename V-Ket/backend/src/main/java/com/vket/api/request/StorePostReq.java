package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StorePostReq")
@Builder
public class StorePostReq {

    @ApiModelProperty(name="섬 카테고리 번호")
    Long islandId;

    @ApiModelProperty(name="섬 내의 상점 번호")
    Long storeIslandNum;

    @ApiModelProperty(name="상점을 연 사람의 ID")
    String userId;

    @ApiModelProperty(name="상점 이름")
    String storeName;

    @ApiModelProperty(name="상점 설명")
    String storeContent;

    @ApiModelProperty(name="상점 URL")
    String storeUrl;
}
