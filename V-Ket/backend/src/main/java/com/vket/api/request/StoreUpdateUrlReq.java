package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StoreUpdateUrlReq")
@Data
public class StoreUpdateUrlReq {

    @ApiModelProperty(name="상점 id")
    Long storeId;

    @ApiModelProperty(name="상점 URL")
    String storeUrl;

}