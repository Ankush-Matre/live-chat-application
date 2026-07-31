import { Client } from '@stomp/stompjs';

const API_URL = import.meta.env.VITE_API_URL;

const WS_ENDPOINT = import.meta.env.VITE_WS_ENDPOINT;

const TOPIC_MESSAGES = import.meta.env.VITE_TOPIC_MESSAGES;

const APP_CHAT = import.meta.env.VITE_APP_CHAT;

const APP_ADD_USER = import.meta.env.VITE_APP_ADD_USER;

const TOPIC_ONLINE_USERS = import.meta.env.VITE_TOPIC_ONLINE_USERS;

const APP_LEAVE_USER = import.meta.env.VITE_APP_LEAVE_USER;


/*
 * ============================================================
 * TYPING INDICATOR CONFIGURATION
 * ============================================================
 */

const TOPIC_TYPING = import.meta.env.VITE_TOPIC_TYPING;

const APP_TYPING = import.meta.env.VITE_APP_TYPING;


let stompClient = null;


/*
 * ============================================================
 * CONNECT WEBSOCKET
 * ============================================================
 */

export const connectWebSocket = (
    onMessageReceived,
    onConnected,
    onOnlineUsersReceived,
    onTypingReceived
) => {

    stompClient = new Client({

        brokerURL:
            API_URL.replace('http', 'ws') +
            WS_ENDPOINT,

        reconnectDelay: 5000,


        /*
         * ========================================================
         * WEBSOCKET CONNECTED
         * ========================================================
         */

        onConnect: () => {

            console.log(
                'Connected to Spring Boot WebSocket'
            );


            /*
             * ====================================================
             * SUBSCRIBE TO CHAT MESSAGES
             * ====================================================
             */
            stompClient.subscribe(TOPIC_TYPING, (message) => {

                const typingEvent = JSON.parse(message.body);

                console.log("Typing Event:", typingEvent);

                if (onTypingReceived) {
                    onTypingReceived(typingEvent);
                }
            });


            /*
             * ====================================================
             * SUBSCRIBE TO ONLINE USERS
             * ====================================================
             */

            stompClient.subscribe(
                TOPIC_ONLINE_USERS,
                (message) => {

                    const onlineUsers =
                        JSON.parse(message.body);

                    console.log(
                        'Online Users Received:',
                        onlineUsers
                    );

                    if (onOnlineUsersReceived) {

                        onOnlineUsersReceived(
                            onlineUsers
                        );
                    }
                }
            );


            /*
             * ====================================================
             * SUBSCRIBE TO TYPING EVENTS
             * ====================================================
             */

            stompClient.subscribe(
                TOPIC_TYPING,
                (message) => {

                    const typingData =
                        JSON.parse(message.body);

                    console.log(
                        'Typing Event:',
                        typingData
                    );

                    if (onTypingReceived) {

                        onTypingReceived(
                            typingData
                        );
                    }
                }
            );


            /*
             * ====================================================
             * CALLBACK AFTER CONNECTION
             * ====================================================
             */

            if (onConnected) {

                onConnected();
            }
        },


        /*
         * ========================================================
         * STOMP ERROR
         * ========================================================
         */

        onStompError: (frame) => {

            console.error(
                'STOMP Error:',
                frame
            );
        },


        /*
         * ========================================================
         * WEBSOCKET ERROR
         * ========================================================
         */

        onWebSocketError: (error) => {

            console.error(
                'WebSocket Error:',
                error
            );
        },


        /*
         * ========================================================
         * DISCONNECTED
         * ========================================================
         */

        onDisconnect: () => {

            console.log(
                'Disconnected from Spring Boot WebSocket'
            );
        }
    });


    stompClient.activate();
};


/*
 * ============================================================
 * SEND CHAT MESSAGE
 * ============================================================
 */

export const sendWebSocketMessage = (message) => {

    if (
        stompClient &&
        stompClient.connected
    ) {

        stompClient.publish({

            destination: APP_CHAT,

            body: JSON.stringify(message)
        });

    } else {

        console.warn(
            'STOMP client not connected. Message not sent.'
        );
    }
};


/*
 * ============================================================
 * SEND TYPING EVENT
 * ============================================================
 */

export const sendTypingMessage = (typingData) => {

    if (stompClient && stompClient.connected) {

        stompClient.publish({
            destination: APP_TYPING,
            body: JSON.stringify(typingData)
        });

    } else {

        console.warn(
            'STOMP client not connected. Typing event not sent.'
        );
    }
};


/*
 * ============================================================
 * SEND JOIN MESSAGE
 * ============================================================
 */

export const sendJoinMessage = (username) => {

    if (
        stompClient &&
        stompClient.connected
    ) {

        stompClient.publish({

            destination: APP_ADD_USER,

            body: JSON.stringify({

                sender: username,

                content:
                    username +
                    ' has joined the chat',

                type: 'JOIN',

                timeStamp:
                    new Date().toISOString()
            })
        });
    }
};


/*
 * ============================================================
 * SEND LEAVE MESSAGE
 * ============================================================
 */

export const sendLeaveMessage = (username) => {

    if (
        stompClient &&
        stompClient.connected
    ) {

        stompClient.publish({

            destination: APP_LEAVE_USER,

            body: JSON.stringify({

                sender: username,

                content:
                    username +
                    ' has left the chat',

                type: 'LEAVE',

                timeStamp:
                    new Date().toISOString()
            })
        });

    } else {

        console.warn(
            'STOMP client not connected. Leave message not sent.'
        );
    }
};


/*
 * ============================================================
 * DISCONNECT WEBSOCKET
 * ============================================================
 */

export const disconnectWebSocket = () => {

    if (stompClient) {

        stompClient.deactivate();

        stompClient = null;
    }
};