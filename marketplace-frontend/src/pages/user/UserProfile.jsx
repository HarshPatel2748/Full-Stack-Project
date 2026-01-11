import React from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../../components/Navbar";
import Footer from "../../components/Footer";

const UserProfile = () => {
    const navigate = useNavigate();

    // Fetch static user details from localStorage
    const user = {
        id: localStorage.getItem("userId"),
        firstName: localStorage.getItem("userFirstName"),
        lastName: localStorage.getItem("userLastName"),
        email: localStorage.getItem("userEmail"),
        phone: localStorage.getItem("userPhone"),
        address: localStorage.getItem("userAddress"),
        createdAt: localStorage.getItem("userCreatedAt")
    };

    const handleLogout = () => {
        localStorage.clear(); // clear session
        navigate("/"); // redirect to home/login
    };

    return (
        <div className="min-h-screen bg-gray-900 text-white flex flex-col">
            <Navbar />

            <main className="flex-1">

                <div className="flex flex-col items-center justify-center p-6 mt-10">
                    <h2 className="text-3xl font-bold mb-6">User Profile</h2>

                    <div className="bg-gray-800 p-6 rounded-lg w-full max-w-md space-y-4">
                        <div>
                            <strong>Full Name:</strong> {user.firstName} {user.lastName}
                        </div>
                        <div>
                            <strong>Email:</strong> {user.email}
                        </div>

                        <button
                            onClick={handleLogout}
                            className="w-full mt-4 bg-red-600 hover:bg-red-700 py-2 rounded-lg font-semibold transition"
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </main>
            <Footer />
        </div>
    );
};

export default UserProfile;
