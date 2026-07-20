import { useState } from 'react';
import { sendWebSocketMessage } from '../services/websocket';

function MessageInput({ username }) {

    const [message, setMessage] = useState('');

    const handleSend = () => {

        if (message.trim() === '') return;

        const payload = {
            sender: username,
            content: message,
            type: 'CHAT',
            timeStamp: new Date().toISOString()
        };

        sendWebSocketMessage(payload);

        setMessage('');
    };

    return (
        <div className="message-input-container">

            <input
                type="text"
                placeholder="Type a message..."
                className="message-input"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                        handleSend();
                    }
                }}
            />

            <button
                className="send-button"
                onClick={handleSend}
            >
                Send
            </button>

        </div>
    );
}

export default MessageInput;