import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SellerLogin = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: "",
    password: ""
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/sellers/login", formData);
      localStorage.setItem("sellerToken", res.data.token); // Assuming backend returns JWT
      setMessage("");
      navigate("/seller/dashboard"); // Redirect to seller dashboard/home
    } catch (err) {
      setMessage(err.response?.data?.message || "Login failed!");
    }
  };

  return (
    <div className="min-h-screen bg-gray-900 flex items-center justify-center">
      <div className="bg-gray-800 p-10 rounded-2xl shadow-2xl w-full max-w-md">
        <h2 className="text-3xl font-bold text-center text-white mb-6">Seller Login</h2>

        {message && (
          <div className="mb-4 text-center text-red-400 font-medium">{message}</div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            required
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            required
          />

          <button
            type="submit"
            className="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700 transition-colors font-semibold"
          >
            Login
          </button>
        </form>

        <p className="mt-4 text-center text-gray-300 text-sm">
          Don't have an account?{" "}
          <a href="/signup/seller" className="text-indigo-500 font-medium hover:underline">
            Signup
          </a>
        </p>
      </div>
    </div>
  );
};

export default SellerLogin;
