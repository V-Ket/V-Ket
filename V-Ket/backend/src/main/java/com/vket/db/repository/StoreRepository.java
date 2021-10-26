package com.vket.db.repository;

import com.vket.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreId(Long storeId);

    Optional<Store> findByUser_UserId(String userId);

    List<Store> findAll();

    @Transactional
    void deleteByStoreId(Long storeId);
}
