package com.vket.api.service;

import com.vket.api.request.DealAddReq;
import com.vket.api.response.MyBuyListRes;
import com.vket.api.response.MySellListRes;
import com.vket.db.entity.Deal;
import com.vket.db.entity.Goods;
import com.vket.db.entity.User;
import com.vket.db.repository.DealRepository;
import com.vket.db.repository.GoodsRepository;
import com.vket.db.repository.PurchaseRepository;
import com.vket.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealServiceImpl implements DealService{


    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public boolean addDeal(DealAddReq dealAddReq) {

        User buyer = userRepository.findByUserId(dealAddReq.getBuyerId()).get();

        Goods goods = goodsRepository.findByGoodsId(dealAddReq.getGoodsId()).get();
        if(goods.getGoodsQuantity() < dealAddReq.getQuantity()){
            return false;
        }
        if (buyer.getUserCredit() < (dealAddReq.getEachPrice() * dealAddReq.getQuantity())){
            return false;
        }

        dealRepository.save(Deal.builder()
                .dealSeller(dealAddReq.getSellerId())
                .user(buyer) // 구매자
                .dealGoodsName(dealAddReq.getGoodsName())
                .dealPrice(dealAddReq.getEachPrice())
                .dealQuantity(dealAddReq.getQuantity())
                .dealImg(dealAddReq.getGoodsImg())
                .purchase(purchaseRepository.findByPurchaseId(10l).get()) //처음 들어오면 결제완료
                .build());

        goods.updateQuantity(goods.getGoodsQuantity() - dealAddReq.getQuantity());
        buyer.updateUserCredit(buyer.getUserCredit() - (dealAddReq.getEachPrice() * dealAddReq.getQuantity()));
        return true;
    }

    @Override
    public Long updateDeal(Long dealId) {

        Deal deal = dealRepository.findByDealId(dealId).get();

        if (deal.getPurchase().getPurchaseId() == 10){
            System.out.println("찾아라ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ");
            deal.updatePurchase(purchaseRepository.findByPurchaseId(11l).get());
            dealRepository.save(deal);
            return 11l;
        }else{
            deal.updatePurchase(purchaseRepository.findByPurchaseId(12l).get());
            dealRepository.save(deal);
            return 12l;
        }

    }

    @Override
    public Long cancelDeal(Long dealId) {

        Deal deal = dealRepository.findByDealId(dealId).get();

        deal.updatePurchase(purchaseRepository.findByPurchaseId(13l).get());
        dealRepository.save(deal);

        return 13l;
    }

    @Override
    public boolean deleteDeal(Long dealId) {

        dealRepository.deleteByDealId(dealId);

        return true;
    }

    @Override
    public List<MySellListRes> mySellList(String userId) {
        List<MySellListRes> mySellListResList = new ArrayList<>();
        List<Deal> deals = dealRepository.findByDealSeller(userId);

        for(Deal d : deals){
            mySellListResList.add(MySellListRes.builder()
                    .buyerId(d.getUser().getUserId())
                    .goodsImg(d.getDealImg())
                    .goodsName(d.getDealGoodsName())
                    .goodsPrice(d.getDealPrice())
                    .goodsQuantity(d.getDealQuantity())
                    .purchaseStatus(d.getPurchase().getPurchaseId())
                    .dealId(d.getDealId())
                    .build());
        }

        return mySellListResList;
    }

    @Override
    public List<MyBuyListRes> myBuyList(String userId) {
        List<MyBuyListRes> myBuyListResList = new ArrayList<>();
        List<Deal> deals = dealRepository.findByUser_UserId(userId);

        for(Deal d : deals){
            myBuyListResList.add(MyBuyListRes.builder()
                    .sellerId(d.getDealSeller())
                    .goodsName(d.getDealGoodsName())
                    .goodsImg(d.getDealImg())
                    .goodsPrice(d.getDealPrice())
                    .goodsQuantity(d.getDealQuantity())
                    .purchaseStatus(d.getPurchase().getPurchaseId())
                    .dealId(d.getDealId())
                    .build());
        }
        return myBuyListResList;
    }

}
