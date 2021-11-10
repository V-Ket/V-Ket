package com.vket.api.service;

import com.vket.api.request.DealAddReq;
import com.vket.api.response.MyBuyListRes;
import com.vket.api.response.MySellListRes;
import com.vket.db.entity.Deal;

import java.util.List;

public interface DealService {

    boolean addDeal(DealAddReq dealAddReq);

    Long updateDeal(Long dealId);

    Long cancelDeal(Long dealId);

    boolean deleteDeal(Long dealId);

    List<MySellListRes> mySellList(String userId);

    List<MyBuyListRes> myBuyList(String userId);
}
