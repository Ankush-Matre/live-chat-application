function Sidebar() {

    const users = ['Ankush', 'Rahul', 'Priya', 'Amit'];

    return (
        <aside className="sidebar">

            <h3>Online Users</h3>

            <ul className="user-list">

                {users.map((user, index) => (

                    <li key={index} className="user-item">

                        <span className="online-dot"></span>

                        {user}

                    </li>

                ))}

            </ul>

        </aside>
    );
}

export default Sidebar;