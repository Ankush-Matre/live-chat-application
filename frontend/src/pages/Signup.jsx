import { register } from "../services/registerService";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useTheme } from "../context/ThemeContext";
import "../styles/login.css";

function Signup() {

    const navigate = useNavigate();

    const { theme, toggleTheme } = useTheme();

    const [formData, setFormData] = useState({

        username: "",

        email: "",

        password: "",

        confirmPassword: ""
    });

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value
        });

    };

    const handleSignup = async () => {

        if (
            !formData.username.trim() ||
            !formData.email.trim() ||
            !formData.password.trim() ||
            !formData.confirmPassword.trim()
        ) {

            alert("Please fill all fields");
            return;
        }

        if (formData.password !== formData.confirmPassword) {

            alert("Passwords do not match");
            return;

        }

        try {

            await register({

                username: formData.username,

                email: formData.email,

                password: formData.password

            });

            alert("Registration Successful");

            navigate("/");

        }

        catch (error) {

            console.error(error);

            alert("Registration Failed");

        }

    };

    return (

        <div className="login-container">

            <button
                className="theme-toggle-floating"
                onClick={toggleTheme}
            >
                {theme === "light" ? "🌙" : "☀️"}
            </button>

            <div className="login-card">

                <h1>💬 Live Chat</h1>

                <p>Create your account</p>

                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={formData.username}
                    onChange={handleChange}
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                />

                <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Confirm Password"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                />

                <button onClick={handleSignup}>
                    Create Account
                </button>

                <p style={{ marginTop: "20px" }}>

                    Already have an account?

                    {" "}

                    <Link to="/">
                        Login
                    </Link>

                </p>

            </div>

        </div>

    );

}

export default Signup;