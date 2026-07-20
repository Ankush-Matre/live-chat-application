import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { connectWebSocket, disconnectWebSocket } from '../services/websocket';

import Header from '../components/Header';
import Sidebar from '../components/Sidebar';
import ChatWindow from '../components/ChatWindow';
import MessageInput from '../components/MessageInput';

import '../styles/chat.css';

function ChatPage() {

    const location = useLocation();

    const username = location.state?.username || 'Guest';
    const [messages, setMessages] = useState([]);

    useEffect(() => {

        connectWebSocket((newMessage) => {

            setMessages((prevMessages) => [
                ...prevMessages,
                newMessage
            ]);
        });

        return () => {
            disconnectWebSocket();
        };

    }, []);

    return (
        <div className="chat-page">

            <Header username={username} />

            <div className="chat-layout">

                <Sidebar />

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