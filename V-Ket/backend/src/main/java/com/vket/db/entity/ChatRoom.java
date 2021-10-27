package com.vket.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private long chatRoomId;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "receiver_id")
    private String receiverId;

//    @Builder
//    public ChatRoom(long chatRoomId, String senderId, String receiverId) {
//        super();
//        this.chatRoomId = chatRoomId;
//        this.senderId = senderId;
//        this.receiverId = receiverId;
//    }
}
