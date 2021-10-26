package com.vket.db.repository;

import com.vket.db.entity.Island;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IslandRepository extends JpaRepository<Island, Long> {

    Island findByIslandId(Long islandId);
}
