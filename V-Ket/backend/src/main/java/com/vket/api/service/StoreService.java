package com.vket.api.service;

import com.vket.api.request.*;
import com.vket.api.response.StoreInfoRes;

import java.util.List;

public interface StoreService {

    boolean addStore(StorePostReq storePostReq);

    boolean updateStoreAll(StoreUpdateAllReq storeUpdateAllReq);

    boolean updateStoreName(StoreUpdateNameReq storeUpdateNameReq);

    boolean updateStoreContent(StoreUpdateContentReq storeUpdateContentReq);

    boolean updateStoreUrl(StoreUpdateUrlReq storeUpdateUrlReq);

    List<StoreInfoRes> allStoreInfo();

    int openClose(Long storeId);

    boolean deleteStore(Long storeId);
}
