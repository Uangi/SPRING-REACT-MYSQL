package com.jyp.boardback.Model;
import javax.persistence.*;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String sender;
    
    private String content;
    
    // Getter and setter methods
}
