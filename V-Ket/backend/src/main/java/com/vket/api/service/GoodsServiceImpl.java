package com.vket.api.service;

import com.vket.api.request.GoodsAddReq;
import com.vket.api.request.GoodsUpdateReq;
import com.vket.api.response.GoodsRes;
import com.vket.db.entity.Goods;
import com.vket.db.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

//    @Override
//    public List<Goods> findAll() {
//        return goodsRepository.findAll();
//    }

    // 상품 전체 정보
    @Override
    public List<GoodsRes> findAllGoods() {
        List<Goods> goodsList = goodsRepository.findAll();
        List<GoodsRes> goodsResList = new ArrayList<GoodsRes>();

        for(Goods goods: goodsList) {
            GoodsRes goodsRes = GoodsRes.builder()
                    .goodsId(goods.getGoodsId())
                    .goodsName(goods.getGoodsName())
                    .goodsPrice(goods.getGoodsPrice())
                    .goodsImg(goods.getGoodsImg())
                    .goodsQuantity(goods.getGoodsQuantity())
                    .goodsContent(goods.getGoodsContent())
                    .storeId(goods.getStore().getStoreId())
                    .build();
            goodsResList.add(goodsRes);
        }

        return goodsResList;
    }

    // 상점 별 전체 상품 정보
    @Override
    public List<GoodsRes> findGoodsByStoreId(Long storeId) {
        List<Goods> goodsListByStoreId = goodsRepository.findByStore_StoreId(storeId);
        List<GoodsRes> goodsResListByStoreId = new ArrayList<GoodsRes>();

        for(Goods goods: goodsListByStoreId) {
            GoodsRes goodsRes = GoodsRes.builder()
                    .goodsId(goods.getGoodsId())
                    .goodsName(goods.getGoodsName())
                    .goodsPrice(goods.getGoodsPrice())
                    .goodsImg(goods.getGoodsImg())
                    .goodsQuantity(goods.getGoodsQuantity())
                    .goodsContent(goods.getGoodsContent())
                    .storeId(goods.getStore().getStoreId())
                    .build();
            goodsResListByStoreId.add(goodsRes);
        }

        return goodsResListByStoreId;
    };

    // 선택된 상품의 상세 정보
    @Override
    public GoodsRes findGoodsByGoodsId(Long goodsId) {
        if(goodsRepository.findByGoodsId(goodsId).isPresent()) {
            Goods goods = goodsRepository.findByGoodsId(goodsId).get();
            return GoodsRes.builder()
                    .goodsId(goods.getGoodsId())
                    .goodsName(goods.getGoodsName())
                    .goodsPrice(goods.getGoodsPrice())
                    .goodsImg(goods.getGoodsImg())
                    .goodsQuantity(goods.getGoodsQuantity())
                    .goodsContent(goods.getGoodsContent())
                    .storeId(goods.getStore().getStoreId())
                    .build();
        } else {
            return null;
        }
    }

    // 상품 정보 수정하기
    @Override
    public boolean updateGoodsInfo(GoodsUpdateReq goodsUpdateReq) {
        if (goodsRepository.findByGoodsId(goodsUpdateReq.getGoodsId()).isPresent()) {
            Goods goods = goodsRepository.findByGoodsId(goodsUpdateReq.getGoodsId()).get();
            goods.updateGoodsInfo(goodsUpdateReq);
            goodsRepository.save(goods);
            return true;
        } else {
            return false;
        }
    }

    // 상품 등록하기
//    @Override
//    public boolean addGoods(GoodsAddReq goodsAddReq) {
//        goodsRepository.save(Goods.builder()
//                .goodsId(goodsAddReq.getGoodsId())
//                .goodsName(goodsAddReq.getGoodsName())
//                .goodsPrice(goodsAddReq.getGoodsPrice())
//                .goodsImg(goodsAddReq.getGoodsImg())
//                .goodsQuantity(goodsAddReq.getGoodsQuantity())
//                .goodsContent(goodsAddReq.getGoodsContent())
//                .build());
//        return true;
//    }

    // 상품 삭제하기
}
