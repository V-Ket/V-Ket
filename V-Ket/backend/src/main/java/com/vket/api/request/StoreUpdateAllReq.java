package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StoreUpdateAllReq")
@Builder
public class StoreUpdateAllReq {

    @ApiModelProperty(name="상점 id")
    Long storeId;

    @ApiModelProperty(name="상점 이름")
    String storeName;

    @ApiModelProperty(name="상점 설명")
    String storeContent;

    @ApiModelProperty(name="상점 URL")
    String storeUrl;

}
