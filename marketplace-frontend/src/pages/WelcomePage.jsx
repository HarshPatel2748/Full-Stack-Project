import React from "react";
import { useNavigate } from "react-router-dom";

const WelcomePage = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gray-900 flex items-center justify-center">
      <div className="bg-gray-800 p-10 rounded-2xl shadow-2xl w-full max-w-md text-center">
        <h1 className="text-4xl font-bold text-white mb-6">Welcome to Marketplace</h1>
        <p className="text-gray-300 mb-8">Get started by signing up or logging in</p>

        {/* Main Buttons for Users */}
        <div className="flex flex-col gap-4 mb-6">
          <button
            onClick={() => navigate("/signup/user ")}
            className="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700 transition-colors font-semibold"
          >
            Signup
          </button>
          <button
            onClick={() => navigate("/login/user")}
            className="w-full bg-gray-700 text-white py-2 rounded-lg hover:bg-gray-600 transition-colors font-semibold"
          >
            Login
          </button>
        </div>

        {/* Links for Seller and Admin */}
        <div className="flex justify-between text-sm text-blue-400 mt-4">
          <span
            className="cursor-pointer hover:underline"
            onClick={() => navigate("/login/seller")}
          >
            Are you a Seller?
          </span>
          <span
            className="cursor-pointer hover:underline"
            onClick={() => navigate("/login/admin")}
          >
            Admin?
          </span>
        </div>
      </div>
    </div>
  );
};

export default WelcomePage;
