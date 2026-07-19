function MessageInput() {

    return (
        <div className="message-input-container">

            <input
                type="text"
                placeholder="Type a message..."
                className="message-input"
            />

            <button className="send-button">
                Send
            </button>

        </div>
    );
}

export default MessageInput;