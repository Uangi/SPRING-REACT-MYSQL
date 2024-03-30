// ChatApp.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import * as StompJs from "@stomp/stompjs";

function ChatApp() {
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState('');

    useEffect(() => {
        fetchMessages();
    }, []);

    const fetchMessages = () => {
        axios.get('/api/chat/messages')
            .then(response => {
                setMessages(response.data);
            })
            .catch(error => {
                console.error('Error fetching messages:', error);
            });
    };

    const sendMessage = () => {
        axios.post('/api/chat/send', { sender: 'User', content: newMessage })
            .then(response => {
                fetchMessages(); // 새로운 메시지를 보내고 나서 메시지 목록을 다시 불러옴
                setNewMessage(''); // 메시지 입력 필드를 비움
            })
            .catch(error => {
                console.error('Error sending message:', error);
            });
    };

    return (
        <div>
            <div>
                {messages.map(message => (
                    <div key={message.id}>
                        <strong>{message.sender}:</strong> {message.content}
                    </div>
                ))}
            </div>
            <div>
                <input type="text" value={newMessage} onChange={e => setNewMessage(e.target.value)} />
                <button onClick={sendMessage}>Send</button>
            </div>
        </div>
    );
}

export default ChatApp;