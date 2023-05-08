package com.example.workwave.services;

import com.example.workwave.entities.ChatRoom;
import com.example.workwave.repositories.ChatRoomRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Builder
public class ChatRoomService {

    @Autowired

    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        if (chatRoomOptional.isPresent()) {
            return Optional.of(chatRoomOptional.get().getChatId());
        } else {
            if (!createIfNotExist) {
                return Optional.empty();
            }
            String chatId = senderId + "_" + recipientId;
            ChatRoom senderRecipient = ChatRoom.builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoom recipientSender = ChatRoom.builder()
                    .chatId(chatId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();
            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);

            return Optional.of(chatId);
        }
    }

    public void deleteById(String id) {
        chatRoomRepository.deleteById(id);
    }

    public void deleteByChatId(String chatId) {
        List<ChatRoom> Rooms = chatRoomRepository.findChatRoomsByChatId(chatId);
        for (ChatRoom c:Rooms) {

            chatRoomRepository.deleteById(c.getId());

        }
    }
    public Optional<ChatRoom>findBySenderIdAndRecipientId(String senderId , String RecipientId){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,RecipientId);
    }
    public List<ChatRoom>findBySenderId(String senderId){
        return chatRoomRepository.findChatRoomsBySenderId(senderId);
    }
}