package com.example.workwave.controllers;

import com.example.workwave.entities.ChatMessage;
import com.example.workwave.entities.ChatNotification;
import com.example.workwave.services.ChatMessageService;
import com.example.workwave.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;


    //{
    //    "id": "1",
    //    "chatId": "1",
    //    "senderId": "2",
    //    "recipientId": "1",
    //    "senderName": "John",
    //    "recipientName": "Jane",
    //    "content": "Hello, Jane!",
    //    "timestamp": "2022-04-12",
    //    "status": "RECEIVED"
    //}
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        Optional<String> chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());
        System.out.println("Received message: " + chatMessage.getContent());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName(),
                        saved.isVideo()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages(@PathVariable String senderId,
                                              @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        chatMessageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        ChatMessage chatMessage = chatMessageService.findById(id);
        chatMessage.setContent(content);
        chatMessageService.save(chatMessage);
        return ResponseEntity.ok(chatMessage);
    }

    @PostMapping("/chat/add")
    public ResponseEntity<?> addMessage(@RequestBody ChatMessage requestBody) {
        Optional<String> chatId = chatRoomService
                .getChatId(requestBody.getSenderId(), requestBody.getRecipientId(), true);
        requestBody.setChatId(chatId.get());
        chatMessageService.save(requestBody);

        return ResponseEntity.ok(requestBody);
    }

    @DeleteMapping("/chatRoom/{id}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable String id) {
        chatRoomService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/chatRoomD/{chatId}")

    public ResponseEntity<?> deleteChatRoomsChatId(@PathVariable String chatId) {
        chatRoomService.deleteByChatId(chatId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chatRooms/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatRoom(@PathVariable String senderId,
                                          @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatRoomService.findBySenderIdAndRecipientId(senderId, recipientId));
    }

    @GetMapping("/chatRooms/{senderId}")
    public ResponseEntity<?> findChatRooms(@PathVariable String senderId) {
        return ResponseEntity
                .ok(chatRoomService.findBySenderId(senderId));
    }
}
//DELETE A CONVERSATION

/*

    public void processMessage(@Payload ChatMessage chatMessage) {//} -> core method

@MessageMapping("/chat") : Cette annotation indique que cette méthode doit être exécutée chaque fois qu'un message avec la destination "/chat" est reçu.

public void processMessage(@Payload ChatMessage chatMessage) : Cette méthode prend en entrée un objet de type ChatMessage, qui est le message envoyé par l'utilisateur.

Optional<String> chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true); : Cette ligne de code utilise le chatRoomService pour récupérer l'ID de la conversation entre l'expéditeur et le destinataire du message.

chatMessage.setChatId(chatId.get()); : Cette ligne de code défini l'ID de la conversation pour le chatMessage.

ChatMessage saved = chatMessageService.save(chatMessage); : Cette ligne de code sauvegarde le chatMessage dans la base de données.

messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages", new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName())); : Cette ligne envoie une notification de nouveau message à l'utilisateur destinataire, en utilisant la méthode convertAndSendToUser() de l'objet messagingTemplate. Cette notification contient l'ID, l'expéditeur et le nom de l'expéditeur du message sauvegardé dans la base de données.

 */