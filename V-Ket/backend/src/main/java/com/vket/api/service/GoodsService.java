package com.vket.api.service;

import com.vket.api.request.GoodsAddReq;
import com.vket.api.request.GoodsUpdateReq;
import com.vket.api.response.GoodsRes;

import java.util.List;
import java.util.Optional;

public interface GoodsService {
//    List<Goods> findAll();
    // 상품 전체 정보
    List<GoodsRes> findAllGoods();

    // 상점 별 전체 상품 정보
    List<GoodsRes> findGoodsByStoreId(Long storeId);

    // 선택된 상품의 상세 정보
    GoodsRes findGoodsByGoodsId(Long goodsId);

    // 상품 정보 수정하기
    boolean updateGoodsInfo(GoodsUpdateReq goodsUpdateReq);

    // 상품 등록하기
    boolean addGoods(GoodsAddReq goodsAddReq);
    
    // 상품 삭제하기
    boolean deleteByGoodsId(Long goodsId);
}
