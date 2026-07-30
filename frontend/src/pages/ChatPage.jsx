import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

import {
    connectWebSocket,
    disconnectWebSocket,
    sendJoinMessage,
    sendLeaveMessage
} from '../services/websocket';

import { getOnlineUsers } from '../services/onlineUserService';
import { getAllMessages } from '../services/messageService';

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

        /*
         * ============================================================
         * STEP 1 : Load old chat messages from database
         * ============================================================
         */

        const loadChatHistory = async () => {

            try {

                const oldMessages = await getAllMessages();

                setMessages(oldMessages);

                console.log(
                    'Chat History:',
                    oldMessages
                );

            } catch (error) {

                console.error(
                    'Failed to load chat history:',
                    error
                );
            }
        };

        loadChatHistory();


        /*
         * ============================================================
         * STEP 2 : Load currently online users
         * ============================================================
         */

        const loadOnlineUsers = async () => {

            try {

                const users = await getOnlineUsers();

                setOnlineUsers(users);

                console.log(
                    'Online Users from REST API:',
                    users
                );

            } catch (error) {

                console.error(
                    'Failed to load online users:',
                    error
                );
            }
        };

        loadOnlineUsers();


        /*
         * ============================================================
         * STEP 3 : Handle incoming WebSocket messages
         * ============================================================
         */

        const handleMessage = (newMessage) => {

            console.log(
                'Received WebSocket Message:',
                newMessage
            );

            setMessages((prevMessages) => [
                ...prevMessages,
                newMessage
            ]);


            /*
             * User JOINED
             */

            if (newMessage.type === 'JOIN') {

                setOnlineUsers((prevUsers) => {

                    if (
                        prevUsers.includes(
                            newMessage.sender
                        )
                    ) {
                        return prevUsers;
                    }

                    return [
                        ...prevUsers,
                        newMessage.sender
                    ];
                });
            }


            /*
             * User LEFT
             */

            if (newMessage.type === 'LEAVE') {

                setOnlineUsers((prevUsers) =>
                    prevUsers.filter(
                        (user) =>
                            user !== newMessage.sender
                    )
                );
            }
        };


        /*
         * ============================================================
         * STEP 4 : Receive online users from WebSocket
         * ============================================================
         */

        const handleOnlineUsers = (users) => {

            console.log(
                'Online Users from WebSocket:',
                users
            );

            setOnlineUsers(users);
        };


        /*
         * ============================================================
         * STEP 5 : WebSocket connected
         * ============================================================
         */

        const handleConnected = () => {

            console.log(
                'WebSocket connected for user:',
                username
            );

            sendJoinMessage(username);
        };


        /*
         * ============================================================
         * STEP 6 : Connect WebSocket
         * ============================================================
         */

        connectWebSocket(
            handleMessage,
            handleConnected,
            handleOnlineUsers
        );


        /*
         * ============================================================
         * STEP 7 : Cleanup
         * ============================================================
         */

        return () => {

            sendLeaveMessage(username);

            disconnectWebSocket();
        };

    }, [username]);


    /*
     * ============================================================
     * Leave Chat
     * ============================================================
     */

    const handleLeaveChat = () => {

        sendLeaveMessage(username);

        disconnectWebSocket();

        navigate('/');
    };


    /*
     * ============================================================
     * UI
     * ============================================================
     */

    return (

        <div className="chat-page">

            <Header
                username={username}
                onLeave={handleLeaveChat}
            />

            <div className="chat-layout">

                <Sidebar
                    onlineUsers={onlineUsers}
                />

                <div className="chat-main">

                    <ChatWindow
                        username={username}
                        messages={messages}
                    />

                    <MessageInput
                        username={username}
                    />

                </div>

            </div>

        </div>
    );
}

export default ChatPage;