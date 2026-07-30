function Message({
                     sender,
                     content,
                     own,
                     type,
                     timeStamp
                 }) {

    /*
     * ============================================================
     * JOIN / LEAVE SYSTEM MESSAGE
     * ============================================================
     */

    if (type === 'JOIN' || type === 'LEAVE') {

        return (
            <div className="system-message">
                {content}
            </div>
        );
    }


    /*
     * ============================================================
     * FORMAT MESSAGE TIME
     * ============================================================
     */

    const formatTime = (timestamp) => {

        if (!timestamp) {
            return '';
        }

        const date = new Date(timestamp);

        if (isNaN(date.getTime())) {
            return '';
        }

        return date.toLocaleTimeString([], {
            hour: '2-digit',
            minute: '2-digit'
        });
    };


    /*
     * ============================================================
     * NORMAL CHAT MESSAGE
     * ============================================================
     */

    return (

        <div className={`message ${own ? 'own' : 'other'}`}>

            <div className="message-bubble">

                <div className="message-sender">
                    {sender}
                </div>

                <div className="message-content">
                    {content}
                </div>

                <div className="message-time">
                    {formatTime(timeStamp)}
                </div>

            </div>

        </div>
    );
}

export default Message;