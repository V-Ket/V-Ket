package com.vket.db.repository;

import com.vket.db.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser_UserSeq(Long userSeq);

    @Transactional
    Long deleteByStoreIdAndUser_UserSeq(Long storeId, Long userseq);
}
