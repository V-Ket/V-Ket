package com.vket.api.service;

import com.vket.api.response.GoodsRes;
import com.vket.db.entity.Goods;
import com.vket.db.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

//    @Override
//    public List<Goods> findAll() {
//        return goodsRepository.findAll();
//    }

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
}
