import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ThemeProvider } from "./context/ThemeContext";

import LoginPage from "./pages/LoginPage";
import Signup from "./pages/Signup";
import ChatPage from "./pages/ChatPage";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {

    return (

        <ThemeProvider>

            <BrowserRouter>

                <Routes>

                    {/* Login */}
                    <Route
                        path="/"
                        element={<LoginPage />}
                    />

                    {/* Signup */}
                    <Route
                        path="/signup"
                        element={<Signup />}
                    />

                    {/* Protected Chat */}
                    <Route
                        path="/chat"
                        element={
                            <ProtectedRoute>
                                <ChatPage />
                            </ProtectedRoute>
                        }
                    />

                </Routes>

            </BrowserRouter>

        </ThemeProvider>

    );
}

export default App;