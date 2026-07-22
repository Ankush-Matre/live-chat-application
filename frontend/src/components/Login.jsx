import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useTheme } from '../context/ThemeContext';
import '../styles/login.css';

function Login() {

    const [username, setUsername] = useState('');

    const navigate = useNavigate();
    const { theme, toggleTheme } = useTheme();

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

            <button className="theme-toggle-floating" onClick={toggleTheme}>
                {theme === 'light' ? '🌙' : '☀️'}
            </button>

            <div className="login-card">

                <h1>💬 Live Chat</h1>

                <p>Welcome Back</p>

                <input
                    type="text"
                    placeholder="Enter Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            handleJoin();
                        }
                    }}
                />

                <button onClick={handleJoin}>
                    Join Chat
                </button>

            </div>

        </div>
    );
}

export default Login;