import Message from './Message';

function ChatWindow({ username, messages }) {

    return (

        <div className="chat-window">

            {messages.length === 0 ? (

                <div className="empty-chat">
                    No messages yet. Send your first message 🚀
                </div>

            ) : (

                messages.map((msg, index) => (

                    <Message
                        key={index}
                        sender={msg.sender}
                        content={msg.content}
                        own={msg.sender === username}
                        type={msg.type}
                        timeStamp={msg.timeStamp}
                    />

                ))
            )}

        </div>
    );
}

export default ChatWindow;