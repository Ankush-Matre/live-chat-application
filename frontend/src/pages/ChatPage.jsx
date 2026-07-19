import { useLocation } from 'react-router-dom';

import Header from '../components/Header';
import Sidebar from '../components/Sidebar';
import ChatWindow from '../components/ChatWindow';
import MessageInput from '../components/MessageInput';

import '../styles/chat.css';

function ChatPage() {

    const location = useLocation();

    const username = location.state?.username || 'Guest';

    return (
        <div className="chat-page">

            <Header username={username} />

            <div className="chat-layout">

                <Sidebar />

                <div className="chat-main">

                    <ChatWindow username={username} />

                    <MessageInput />

                </div>

            </div>

        </div>
    );
}

export default ChatPage;