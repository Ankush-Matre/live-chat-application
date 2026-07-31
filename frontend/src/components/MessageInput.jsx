import { useEffect, useRef, useState } from 'react';

import {
    sendWebSocketMessage,
    sendTypingMessage
} from '../services/websocket';

import '../styles/messageInput.css';

function MessageInput({ username }) {

    const [message, setMessage] = useState('');

    const typingTimeoutRef = useRef(null);

    const isTypingRef = useRef(false);


    /*
     * ============================================================
     * HANDLE INPUT CHANGE
     * ============================================================
     */

    const handleChange = (event) => {

        const value = event.target.value;

        setMessage(value);


        /*
         * If input is empty
         */
        if (!value.trim()) {

            if (isTypingRef.current) {

                sendTypingMessage({
                    sender: username,
                    typing: false
                });

                isTypingRef.current = false;
            }

            return;
        }


        /*
         * Send TRUE only when typing starts.
         *
         * We DON'T send true for every character.
         */
        if (!isTypingRef.current) {

            sendTypingMessage({
                sender: username,
                typing: true
            });

            isTypingRef.current = true;
        }


        /*
         * Reset existing timeout
         */
        if (typingTimeoutRef.current) {

            clearTimeout(typingTimeoutRef.current);
        }


        /*
         * If user doesn't type anything for 1 second,
         * consider typing finished.
         */
        typingTimeoutRef.current = setTimeout(() => {

            sendTypingMessage({
                sender: username,
                typing: false
            });

            isTypingRef.current = false;

        }, 1000);
    };


    /*
     * ============================================================
     * SEND MESSAGE
     * ============================================================
     */

    const handleSend = () => {

        if (!message.trim()) {
            return;
        }


        /*
         * Stop typing indicator
         */
        if (isTypingRef.current) {

            sendTypingMessage({
                sender: username,
                typing: false
            });

            isTypingRef.current = false;
        }


        /*
         * Send message
         */
        sendWebSocketMessage({

            sender: username,

            content: message.trim(),

            type: 'CHAT',

            timeStamp: new Date().toISOString()
        });


        setMessage('');


        /*
         * Clear typing timeout
         */
        if (typingTimeoutRef.current) {

            clearTimeout(typingTimeoutRef.current);

            typingTimeoutRef.current = null;
        }
    };


    /*
     * ============================================================
     * ENTER KEY
     * ============================================================
     */

    const handleKeyDown = (event) => {

        if (event.key === 'Enter') {

            event.preventDefault();

            handleSend();
        }
    };


    /*
     * ============================================================
     * CLEANUP
     * ============================================================
     */

    useEffect(() => {

        return () => {

            if (typingTimeoutRef.current) {

                clearTimeout(typingTimeoutRef.current);
            }


            if (isTypingRef.current) {

                sendTypingMessage({
                    sender: username,
                    typing: false
                });

                isTypingRef.current = false;
            }
        };

    }, [username]);


    return (

        <div className="message-input-container">

            <input
                className="message-input"
                type="text"
                placeholder="Type a message..."
                value={message}
                onChange={handleChange}
                onKeyDown={handleKeyDown}
            />

            <button
                className="send-button"
                onClick={handleSend}
                disabled={!message.trim()}
            >
                Send
            </button>

        </div>
    );
}

export default MessageInput;