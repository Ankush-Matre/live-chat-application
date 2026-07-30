import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useTheme } from '../context/ThemeContext';
import { login } from '../services/authService';
import '../styles/login.css';

function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();
    const { theme, toggleTheme } = useTheme();

    const handleJoin = async () => {

        if (!username.trim() || !password.trim()) {
            alert("Please enter username and password");
            return;
        }

        try {

            const response = await login(username, password);

            // Save JWT Token
            localStorage.setItem("token", response.token);

            // Navigate to Chat Page
            navigate('/chat', {
                state: {
                    username: response.username
                }
            });

        } catch (error) {

            alert("Invalid Username or Password");
            console.error(error);

        }
    };

    return (

        <div className="login-container">

            <button
                className="theme-toggle-floating"
                onClick={toggleTheme}
            >
                {theme === 'light' ? '🌙' : '☀️'}
            </button>

            <div className="login-card">

                <h1>💬 Live Chat</h1>

                <p>Welcome Back</p>

                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            handleJoin();
                        }
                    }}
                />

                <button onClick={handleJoin}>
                    Login
                </button>

                {/* Signup Link */}
                <p style={{ marginTop: "20px" }}>
                    Don't have an account?{" "}
                    <Link to="/signup">
                        Sign Up
                    </Link>
                </p>

            </div>

        </div>

    );
}

export default Login;