package com.vket.db.repository;

import com.vket.db.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long > {

    Optional<Purchase> findByPurchaseId(Long purchaseId);


}
