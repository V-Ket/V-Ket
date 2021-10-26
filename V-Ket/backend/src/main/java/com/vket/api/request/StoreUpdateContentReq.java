package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StoreUpdateContentReq")
@Data
public class StoreUpdateContentReq {

    @ApiModelProperty(name="상점 id")
    Long storeId;

    @ApiModelProperty(name="상점 설명")
    String storeContent;

}