import Message from './Message';

function ChatWindow({ username }) {

    const messages = [
        { sender: 'Rahul', content: 'Hey Ankush 👋', own: false },
        { sender: username, content: 'Hi! React routing is working.', own: true },
        { sender: 'Priya', content: 'Nice UI so far!', own: false }
    ];

    return (
        <div className="chat-window">

            {messages.map((msg, index) => (
                <Message
                    key={index}
                    sender={msg.sender}
                    content={msg.content}
                    own={msg.own}
                />
            ))}

        </div>
    );
}

export default ChatWindow;