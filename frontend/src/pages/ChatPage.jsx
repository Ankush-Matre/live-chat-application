import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import {
    connectWebSocket,
    disconnectWebSocket,
    sendJoinMessage,
    sendLeaveMessage
} from '../services/websocket';

import Header from '../components/Header';
import Sidebar from '../components/Sidebar';
import ChatWindow from '../components/ChatWindow';
import MessageInput from '../components/MessageInput';

import '../styles/chat.css';

function ChatPage() {

    const location = useLocation();
    const navigate = useNavigate();

    const username = location.state?.username || 'Guest';

    const [messages, setMessages] = useState([]);
    const [onlineUsers, setOnlineUsers] = useState([]);

    useEffect(() => {

        const handleMessage = (newMessage) => {

            setMessages((prevMessages) => [...prevMessages, newMessage]);

            if (newMessage.type === 'JOIN') {
                setOnlineUsers((prevUsers) => {
                    if (prevUsers.includes(newMessage.sender)) {
                        return prevUsers;
                    }
                    return [...prevUsers, newMessage.sender];
                });
            }

            if (newMessage.type === 'LEAVE') {
                setOnlineUsers((prevUsers) =>
                    prevUsers.filter((user) => user !== newMessage.sender)
                );
            }
        };

        const handleConnected = () => {
            sendJoinMessage(username);
        };

        connectWebSocket(handleMessage, handleConnected);

        return () => {
            sendLeaveMessage(username);
            disconnectWebSocket();
        };

    }, []);

    const handleLeaveChat = () => {
        sendLeaveMessage(username);
        disconnectWebSocket();
        navigate('/');
    };

    return (
        <div className="chat-page">

            <Header username={username} onLeave={handleLeaveChat} />

            <div className="chat-layout">

                <Sidebar onlineUsers={onlineUsers} />

                <div className="chat-main">

                    <ChatWindow
                        username={username}
                        messages={messages}
                    />

                    <MessageInput username={username} />

                </div>

            </div>

        </div>
    );
}

export default ChatPage;