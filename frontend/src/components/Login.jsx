import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/login.css';

function Login() {

    const [username, setUsername] = useState('');

    const navigate = useNavigate();

    const handleJoin = () => {

        if (username.trim() === '') {
            alert('Please enter a username');
            return;
        }

        navigate('/chat', {
            state: {
                username: username
            }
        });
    };

    return (
        <div className="login-container">

            <div className="login-card">

                <h1>💬 Live Chat</h1>

                <p>Welcome Back</p>

                <input
                    type="text"
                    placeholder="Enter Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <button onClick={handleJoin}>
                    Join Chat
                </button>

            </div>

        </div>
    );
}

export default Login;