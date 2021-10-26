package com.vket.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("UserFavoriteGetResponse")
public class UserFavortieGetRes {

	@ApiModelProperty(name="상점 Id", example = "store_id")
	Long storeId;

	@ApiModelProperty(name="상점 Name", example = "your_name")
	String storeName;

	@ApiModelProperty(name="상점 카테고리", example = "island_id")
	Long islandId;

	@ApiModelProperty(name="상점 상점 위치", example = "store_Island_num")
	Long storeIslandNum;

}
