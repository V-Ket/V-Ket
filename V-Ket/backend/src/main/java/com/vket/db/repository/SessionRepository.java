package com.vket.db.repository;

import com.vket.db.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser_UserId(String userId);
    Optional<Session> findBySessionName(String sessionName);
    @Transactional
    void deleteBySessionName(String sessionName);
}
