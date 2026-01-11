import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Footer from "../../components/Footer";

const AdminDashboard = () => {
    const navigate = useNavigate();

    // Get admin from localStorage
    const admin = JSON.parse(localStorage.getItem("admin"));
    const adminId = admin?.adminId;

    const [pendingSellers, setPendingSellers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    // Redirect if admin not logged in
    useEffect(() => {
        if (!admin || !adminId) {
            navigate("/login/admin");
        }
    }, [admin, adminId, navigate]);

    // Fetch pending sellers
    const fetchPendingSellers = async () => {
        try {
            setLoading(true);
            const res = await axios.get(
                "http://localhost:8080/api/admin/sellers/pending"
            );
            setPendingSellers(res.data);
            setError("");
        } catch (err) {
            setError("Failed to fetch pending sellers");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchPendingSellers();
    }, []);

    // Approve seller
    const approveSeller = async (sellerId) => {
        try {
            await axios.put(
                `http://localhost:8080/api/admin/sellers/${sellerId}/approve/${adminId}`
            );
            fetchPendingSellers();
        } catch (err) {
            alert("Failed to approve seller");
        }
    };

    // Reject seller
    const rejectSeller = async (sellerId) => {
        try {
            await axios.put(
                `http://localhost:8080/api/admin/sellers/${sellerId}/reject/${adminId}`
            );
            fetchPendingSellers();
        } catch (err) {
            alert("Failed to reject seller");
        }
    };

    // Logout
    const logout = () => {
        localStorage.removeItem("admin");
        navigate("/");
    };

    return (
        <div className="min-h-screen flex flex-col bg-gray-900 text-white">
            {/* Header */}
            <header className="bg-gray-800 px-8 py-5 flex justify-between items-center shadow-md">
                <h1 className="text-2xl font-bold">Admin Dashboard</h1>
                <button
                    onClick={logout}
                    className="bg-red-600 hover:bg-red-700 px-4 py-2 rounded-lg font-semibold transition"
                >
                    Logout
                </button>
            </header>

            {/* Main Content */}
            <main className="grow p-8">
                <h2 className="text-xl font-semibold mb-6">
                    Pending Seller Approvals
                </h2>

                {loading && (
                    <p className="text-gray-400">Loading sellers...</p>
                )}

                {error && (
                    <p className="text-red-400 mb-4">{error}</p>
                )}

                {!loading && pendingSellers.length === 0 && (
                    <p className="text-gray-400">
                        No pending sellers 🎉
                    </p>
                )}

                {!loading && pendingSellers.length > 0 && (
                    <div className="overflow-x-auto">
                        <table className="w-full bg-gray-800 rounded-xl overflow-hidden">
                            <thead className="bg-gray-700 text-gray-300">
                                <tr>
                                    <th className="p-4 text-left">Shop Name</th>
                                    <th className="p-4 text-left">Owner Name</th>
                                    <th className="p-4 text-left">Email</th>
                                    <th className="p-4 text-center">Actions</th>
                                </tr>
                            </thead>

                            <tbody>
                                {pendingSellers.map((seller) => (
                                    <tr
                                        key={seller.id}
                                        className="border-b border-gray-700 hover:bg-gray-700 transition"
                                    >
                                        <td className="p-4">{seller.shopName}</td>
                                        <td className="p-4">{seller.ownerName}</td>
                                        <td className="p-4">{seller.email}</td>
                                        <td className="p-4 text-center space-x-3">
                                            <button
                                                onClick={() => approveSeller(seller.id)}
                                                className="bg-green-600 hover:bg-green-700 px-3 py-1 rounded-lg font-medium transition"
                                            >
                                                Approve
                                            </button>
                                            <button
                                                onClick={() => rejectSeller(seller.id)}
                                                className="bg-red-600 hover:bg-red-700 px-3 py-1 rounded-lg font-medium transition"
                                            >
                                                Reject
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </main>

            {/* Footer */}
            <Footer />
        </div>
    );
};

export default AdminDashboard;
