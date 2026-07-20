import { Client } from '@stomp/stompjs';

const API_URL = import.meta.env.VITE_API_URL;
const WS_ENDPOINT = import.meta.env.VITE_WS_ENDPOINT;
const TOPIC_MESSAGES = import.meta.env.VITE_TOPIC_MESSAGES;
const APP_CHAT = import.meta.env.VITE_APP_CHAT;

let stompClient = null;

export const connectWebSocket = (onMessageReceived) => {

    stompClient = new Client({

        brokerURL: API_URL.replace('http', 'ws') + WS_ENDPOINT,

        reconnectDelay: 5000,

        debug: (str) => {
            console.log('[STOMP]', str);
        },

        onConnect: () => {

            console.log('Connected to Spring Boot WebSocket');

            stompClient.subscribe(TOPIC_MESSAGES, (message) => {

                console.log('RAW MESSAGE RECEIVED:', message.body);

                const receivedMessage = JSON.parse(message.body);

                onMessageReceived(receivedMessage);
            });
        },

        onStompError: (frame) => {
            console.error('STOMP Error:', frame.headers, frame.body);
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

    console.log('SENDING PAYLOAD:', message);

    if (stompClient && stompClient.connected) {

        stompClient.publish({
            destination: APP_CHAT,
            body: JSON.stringify(message)
        });
    } else {
        console.warn('STOMP client not connected. Message not sent.');
    }
};

export const disconnectWebSocket = () => {

    if (stompClient) {
        stompClient.deactivate();
    }
};