package com.vket.api.service;

import com.vket.api.request.GoodsAddReq;
import com.vket.api.request.GoodsUpdateReq;
import com.vket.api.response.GoodsRes;
import com.vket.db.entity.Goods;
import com.vket.db.entity.Store;
import com.vket.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.vket.config.DirPathConfig.baseDir;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    S3Service s3Service;

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
    public boolean updateGoodsInfo(GoodsUpdateReq goodsUpdateReq) throws IOException {
        if (goodsRepository.findByGoodsId(goodsUpdateReq.getGoodsId()).isPresent()) {

            Goods goods = goodsRepository.findByGoodsId(goodsUpdateReq.getGoodsId()).get();

            String fileURL;
            if(goodsUpdateReq.getGoodsImg() != null && !goodsUpdateReq.getGoodsImg().isEmpty()){
                // 기존에 있던 이미지 지우기
                s3Service.delete(goods.getGoodsImg());
                // 새로운 이미지로 S3에 등록
                fileURL = s3Service.upload(goodsUpdateReq.getGoodsImg(), "static");
            }else{
                fileURL = goods.getGoodsImg();
            }

            goods.setGoodsName(goodsUpdateReq.getGoodsName());
            goods.setGoodsPrice(goodsUpdateReq.getGoodsPrice());
            goods.setGoodsImg(fileURL);
            goods.setGoodsQuantity(goodsUpdateReq.getGoodsQuantity());
            goods.setGoodsContent(goodsUpdateReq.getGoodsContent());

            goodsRepository.save(goods);
            return true;
        } else {
            return false;
        }
    }

    // 상품 등록하기
    @Override
    public boolean addGoods(GoodsAddReq goodsAddReq) throws IOException {
        Store store = storeRepository.findByStoreId(goodsAddReq.getStoreId()).get();

        String fileURL = s3Service.upload(goodsAddReq.getGoodsImg(), "static");
        if(storeRepository.findByStoreId(goodsAddReq.getStoreId()).isPresent()) {
            goodsRepository.save(Goods.builder()
//                    .goodsId(goodsAddReq.getGoodsId())
                    .goodsName(goodsAddReq.getGoodsName())
                    .goodsPrice(goodsAddReq.getGoodsPrice())
                    .goodsImg(fileURL)
                    .goodsQuantity(goodsAddReq.getGoodsQuantity())
                    .goodsContent(goodsAddReq.getGoodsContent())
                    .store(store)
                    .build());
            return true;
        } else {
            return false;
        }
    }

    // 상품 삭제하기
    @Override
    public boolean deleteByGoodsId(Long goodsId) {
        if (goodsRepository.findByGoodsId(goodsId).isPresent()) {
            goodsRepository.deleteByGoodsId(goodsId);
            return true;
        } else {
            return false;
        }
    }

}
