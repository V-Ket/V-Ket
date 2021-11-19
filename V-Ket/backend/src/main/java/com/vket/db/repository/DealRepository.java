package com.vket.db.repository;

import com.vket.db.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {

    Optional<Deal> findByDealId(Long dealId);

    List<Deal> findByUser_UserId(String userId);

    List<Deal> findByDealSeller(String dealSeller);

    @Transactional
    void deleteByDealId(Long dealId);
}
