import '../styles/header.css';

function Header({ username }) {

    return (
        <header className="header">

            <div className="logo">
                💬 Live Chat
            </div>

            <div className="header-user">
                Welcome, <span>{username}</span>
            </div>

        </header>
    );
}

export default Header;