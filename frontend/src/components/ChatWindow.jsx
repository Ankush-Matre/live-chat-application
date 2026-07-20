import Message from './Message';

function ChatWindow({ username, messages }) {
    return (
        <div className="chat-window">
            {messages.length === 0 ? (
                <div style={{ color: '#6b7280' }}>
                    No messages yet. Send your first message 🚀
                </div>
            ) : (
                messages.map((msg, index) => (
                    <Message
                        key={index}
                        sender={msg.sender}
                        content={msg.content}
                        own={msg.sender === username}
                    />
                ))
            )}
        </div>
    );
}

export default ChatWindow;