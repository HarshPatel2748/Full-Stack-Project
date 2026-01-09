import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SellerSignup = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    shopName: "",
    ownerName: "",
    email: "",
    password: "",
    phone: "",
    address: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/sellers/signup", formData);
      setMessage("");
      navigate("/login/seller"); // Redirect to seller login after signup
    } catch (err) {
      setMessage(err.response?.data?.message || "Signup failed!");
    }
  };

  return (
    <div className="min-h-screen bg-gray-900 flex items-center justify-center">
      <div className="bg-gray-800 p-10 rounded-2xl shadow-2xl w-full max-w-md">
        <h2 className="text-3xl font-bold text-center text-white mb-6">Seller Signup</h2>

        {message && (
          <div className="mb-4 text-center text-red-400 font-medium">{message}</div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="shopName"
            placeholder="Shop Name"
            value={formData.shopName}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            required
          />
          <input
            type="text"
            name="ownerName"
            placeholder="Owner Name"
            value={formData.ownerName}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            required
          />
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
          <input
            type="text"
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={formData.address}
            onChange={handleChange}
            className="w-full px-4 py-2 bg-gray-700 text-white border border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <button
            type="submit"
            className="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700 transition-colors font-semibold"
          >
            Signup
          </button>
        </form>

        <p className="mt-4 text-center text-gray-300 text-sm">
          Already have an account?{" "}
          <a href="/login/seller" className="text-indigo-500 font-medium hover:underline">
            Login
          </a>
        </p>
      </div>
    </div>
  );
};

export default SellerSignup;
