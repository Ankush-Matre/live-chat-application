function Message({ sender, content, own, type }) {

    if (type === 'JOIN' || type === 'LEAVE') {
        return (
            <div className="system-message">
                {content}
            </div>
        );
    }

    return (
        <div className={`message ${own ? 'own' : 'other'}`}>

            <div className="message-bubble">

                <div className="message-sender">
                    {sender}
                </div>

                <div className="message-content">
                    {content}
                </div>

            </div>

        </div>
    );
}

export default Message;