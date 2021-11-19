package com.vket.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StoreUpdateNameReq")
@Data
public class StoreUpdateNameReq {

    @ApiModelProperty(name="상점 id")
    Long storeId;

    @ApiModelProperty(name="상점 이름")
    String storeName;

}
