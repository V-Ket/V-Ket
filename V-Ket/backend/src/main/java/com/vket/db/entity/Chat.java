package com.vket.db.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "chat_session")
    private Long chatSession;

    @Column(name = "chat_user1")
    private Long chatUser1;

    @Column(name = "chat_user2")
    private Long chatUser2;

    @Column(name = "chat_content")
    private String chatContent;
}
