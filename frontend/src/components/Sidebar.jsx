function Sidebar({ onlineUsers }) {

    return (
        <aside className="sidebar">

            <h3>Online Users ({onlineUsers.length})</h3>

            <ul className="user-list">

                {onlineUsers.length === 0 ? (
                    <li className="no-users">No users online</li>
                ) : (
                    onlineUsers.map((user, index) => (
                        <li key={index} className="user-item">
                            <span className="online-dot"></span>
                            {user}
                        </li>
                    ))
                )}

            </ul>

        </aside>
    );
}

export default Sidebar;