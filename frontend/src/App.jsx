import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from './context/ThemeContext';

import LoginPage from './pages/LoginPage';
import ChatPage from './pages/ChatPage';

function App() {
    return (
        <ThemeProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginPage />} />
                    <Route path="/chat" element={<ChatPage />} />
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
}

export default App;