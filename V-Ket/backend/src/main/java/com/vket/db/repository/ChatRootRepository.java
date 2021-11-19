package com.vket.db.repository;

import com.vket.db.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRootRepository extends JpaRepository<ChatRoom, Long>{
    List<ChatRoom> findAll();

    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String receiverId);
//    List<ChatRoom> findAllBySenderId();
//    List<ChatRoom> findAllByReceiverId();
}
