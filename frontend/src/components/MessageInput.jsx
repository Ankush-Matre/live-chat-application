import { useState } from 'react';
import { sendWebSocketMessage } from '../services/websocket';
import '../styles/messageInput.css';

function MessageInput({ username }) {

    const [message, setMessage] = useState('');

    const handleSend = () => {

        if (!message.trim()) {
            return;
        }

        sendWebSocketMessage({
            sender: username,
            content: message.trim(),
            type: 'CHAT',
            timeStamp: new Date().toISOString()
        });

        setMessage('');
    };


    const handleKeyDown = (event) => {

        if (event.key === 'Enter') {
            handleSend();
        }
    };


    return (
        <div className="message-input-container">

            <input
                className="message-input"
                type="text"
                placeholder="Type a message..."
                value={message}
                onChange={(e) => setMessage(e.target.value)}
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