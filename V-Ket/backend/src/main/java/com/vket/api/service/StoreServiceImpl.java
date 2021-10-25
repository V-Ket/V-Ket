package com.vket.api.service;

import com.vket.api.request.*;
import com.vket.api.response.StoreInfoRes;
import com.vket.db.entity.Store;
import com.vket.db.repository.IslandRepository;
import com.vket.db.repository.StoreRepository;
import com.vket.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IslandRepository islandRepository;

    @Override
    public boolean addStore(StorePostReq storePostReq) {

        storeRepository.save(Store.builder()
                .user(userRepository.findByUserId(storePostReq.getUserId()).get())
                .island(islandRepository.findByIslandId(storePostReq.getIslandId()).get())
                .storeName(storePostReq.getStoreName())
                .storeContent(storePostReq.getStoreContent())
                .storeUrl(storePostReq.getStoreUrl())
                .storeStatus(0l)
                .storeIslandNum(storePostReq.getStoreIslandNum())
                .build());

        return true;
    }

    @Override
    public boolean updateStoreAll(StoreUpdateAllReq storeUpdateAllReq) {
        Store store = storeRepository.findByStoreId(storeUpdateAllReq.getStoreId()).get();

        storeRepository.save(store.builder()
                .storeName(storeUpdateAllReq.getStoreName())
                .storeContent(storeUpdateAllReq.getStoreContent())
                .storeUrl(storeUpdateAllReq.getStoreUrl())
                .build());

        return true;
    }

    @Override
    public boolean updateStoreName(StoreUpdateNameReq storeUpdateNameReq) {
        Store store = storeRepository.findByStoreId(storeUpdateNameReq.getStoreId()).get();

        store.setStoreName(storeUpdateNameReq.getStoreName());
        storeRepository.save(store);

        return true;
    }

    @Override
    public boolean updateStoreContent(StoreUpdateContentReq storeUpdateContentReq) {
        Store store = storeRepository.findByStoreId(storeUpdateContentReq.getStoreId()).get();

        store.setStoreContent(storeUpdateContentReq.getStoreContent());
        storeRepository.save(store);

        return true;
    }

    @Override
    public boolean updateStoreUrl(StoreUpdateUrlReq storeUpdateUrlReq) {
        Store store = storeRepository.findByStoreId(storeUpdateUrlReq.getStoreId()).get();

        store.setStoreUrl(storeUpdateUrlReq.getStoreUrl());
        storeRepository.save(store);

        return true;
    }

    @Override
    public List<StoreInfoRes> allStoreInfo() {
        List<StoreInfoRes> storeInfoRes = new ArrayList<>();
        List<Store> stores = storeRepository.findAll();

        for (Store s : stores) {
            storeInfoRes.add(StoreInfoRes.builder()
                    .storeId(s.getStoreId())
                    .userId(s.getUser().getUserId())
                    .islandId(s.getIsland().getIslandId())
                    .storeIslandNum(s.getStoreIslandNum())
                    .storeName(s.getStoreName())
                    .storeContent(s.getStoreContent())
                    .storeUrl(s.getStoreUrl())
                    .storeStatus(s.getStoreStatus())
                    .build());
        }

        return storeInfoRes;
    }

    @Override
    public int openClose(Long storeId) {
        Store store = storeRepository.findByStoreId(storeId).get();
        // 0: Close  // 1: Open
        if (store.getStoreStatus() == 1l) {
            store.setStoreStatus(0l);
            return 0;
        } else {
            store.setStoreStatus(1l);
            return 1;
        }
    }

    @Override
    public boolean deleteStore(Long storeId) {
        storeRepository.deleteByStoreId(storeId);
        return true;
    }

}
