package com.example.workwave.repositories;

import com.example.workwave.entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
    List<ChatRoom>findChatRoomsBySenderId(String senderId);
    // void deleteChatRoomByChatId (String chatId);
    List <ChatRoom> findChatRoomsByChatId(String chatId);
}