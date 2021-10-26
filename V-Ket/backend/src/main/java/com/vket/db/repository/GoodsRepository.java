package com.vket.db.repository;

import com.vket.db.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> findAll(); // 전체 상품 정보 가져오기

    // 상점 별 전체 상품 정보
    List<Goods> findByStore_StoreId(Long storeId);

    // 선택된 상품의 상세 정보
    Optional<Goods> findByGoodsId(Long goodsId);

    // 상품 정보 수정하기

    // 상품 등록하기

    // 상품 삭제하기
}
