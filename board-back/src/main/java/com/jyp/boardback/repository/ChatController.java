// package com.jyp.boardback.repository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.*;

// import com.jyp.boardback.Model.ChatMessage;

// import java.util.List;
// @SpringBootApplication
// @RestController
// @RequestMapping("/api/chat")
// public class ChatController {

//     @Autowired
//     private ChatMessageRepository messageRepository;

//     @GetMapping("/messages")
//     public List<ChatMessage> getAllMessages() {
//         return messageRepository.findAll();
//     }

//     @PostMapping("/send")
//     public ChatMessage sendMessage(@RequestBody ChatMessage message) {
//         return messageRepository.save(message);
//     }
// }