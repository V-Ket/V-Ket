package com.vket.db.repository;

import com.vket.db.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRootRepository extends JpaRepository<ChatRoom, Long>{
    List<ChatRoom> findAll();
//    List<ChatRoom> findAllBySenderId();
//    List<ChatRoom> findAllByReceiverId();
}
