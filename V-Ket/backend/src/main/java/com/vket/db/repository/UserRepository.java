package com.vket.db.repository;

import com.vket.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userSeq);

    Optional<User> findByUserNickname(String userNickname);
}
