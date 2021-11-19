package com.vket.db.repository;

import com.vket.db.entity.Island;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IslandRepository extends JpaRepository<Island, Long> {
    Optional<Island> findByIslandId(Long islandId);
}
