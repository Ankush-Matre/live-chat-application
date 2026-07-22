import { useTheme } from '../context/ThemeContext';
import '../styles/header.css';

function Header({ username, onLeave }) {

    const { theme, toggleTheme } = useTheme();

    return (
        <header className="header">

            <div className="logo">
                💬 Live Chat
            </div>

            <div className="header-right">

                <button className="theme-toggle" onClick={toggleTheme}>
                    {theme === 'light' ? '🌙' : '☀️'}
                </button>

                <div className="header-user">
                    Welcome, <span>{username}</span>
                </div>

                <button className="leave-button" onClick={onLeave}>
                    Leave Chat
                </button>

            </div>

        </header>
    );
}

export default Header;