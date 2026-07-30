import { Client } from '@stomp/stompjs';

const API_URL = import.meta.env.VITE_API_URL;
const WS_ENDPOINT = import.meta.env.VITE_WS_ENDPOINT;
const TOPIC_MESSAGES = import.meta.env.VITE_TOPIC_MESSAGES;
const APP_CHAT = import.meta.env.VITE_APP_CHAT;
const APP_ADD_USER = import.meta.env.VITE_APP_ADD_USER;
const TOPIC_ONLINE_USERS = import.meta.env.VITE_TOPIC_ONLINE_USERS;
const APP_LEAVE_USER = import.meta.env.VITE_APP_LEAVE_USER;

let stompClient = null;

export const connectWebSocket = (
    onMessageReceived,
    onConnected,
    onOnlineUsersReceived
) => {

    stompClient = new Client({

        brokerURL: API_URL.replace('http', 'ws') + WS_ENDPOINT,

        reconnectDelay: 5000,

        onConnect: () => {

            console.log('Connected to Spring Boot WebSocket');

            // Subscribe to chat messages
            stompClient.subscribe(TOPIC_MESSAGES, (message) => {

                const receivedMessage = JSON.parse(message.body);

                onMessageReceived(receivedMessage);
            });

            // Subscribe to online users
            stompClient.subscribe(TOPIC_ONLINE_USERS, (message) => {

                const onlineUsers = JSON.parse(message.body);

                console.log("Online Users Received:", onlineUsers);

                if (onOnlineUsersReceived) {
                    onOnlineUsersReceived(onlineUsers);
                }
            });

            if (onConnected) {
                onConnected();
            }
        },

        onStompError: (frame) => {
            console.error('STOMP Error:', frame);
        },

        onWebSocketError: (error) => {
            console.error('WebSocket Error:', error);
        },

        onDisconnect: () => {
            console.log('Disconnected from Spring Boot WebSocket');
        }
    });

    stompClient.activate();
};

export const sendWebSocketMessage = (message) => {

    if (stompClient && stompClient.connected) {

        stompClient.publish({
            destination: APP_CHAT,
            body: JSON.stringify(message)
        });
    } else {
        console.warn('STOMP client not connected. Message not sent.');
    }
};

export const sendJoinMessage = (username) => {

    if (stompClient && stompClient.connected) {

        stompClient.publish({
            destination: APP_ADD_USER,
            body: JSON.stringify({
                sender: username,
                content: username + ' has joined the chat',
                type: 'JOIN',
                timeStamp: new Date().toISOString()
            })
        });
    }
};

export const sendLeaveMessage = (username) => {

    if (stompClient && stompClient.connected) {

        stompClient.publish({
            destination: APP_LEAVE_USER,
            body: JSON.stringify({
                sender: username,
                content: username + ' has left the chat',
                type: 'LEAVE',
                timeStamp: new Date().toISOString()
            })
        });

    } else {

        console.warn(
            'STOMP client not connected. Leave message not sent.'
        );
    }
};

export const disconnectWebSocket = () => {

    if (stompClient) {
        stompClient.deactivate();
    }
};