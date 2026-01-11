import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import SellerNavbar from "../../components/SellerNavbar";
import Footer from "../../components/Footer";
import axios from "axios";

const SellerProfile = () => {
    const navigate = useNavigate();
    const [seller, setSeller] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    // ✅ Get sellerId from localStorage
    const sellerId = localStorage.getItem("sellerId");

    useEffect(() => {
        if (!sellerId) {
            // Seller not logged in
            navigate("/");
            return;
        }

        const fetchProfile = async () => {
            try {
                const res = await axios.get(
                    `http://localhost:8080/api/sellers/${sellerId}/profile`
                );
                setSeller(res.data);
            } catch (err) {
                console.error(err);
                setError("Failed to load profile");
            } finally {
                setLoading(false);
            }
        };

        fetchProfile();
    }, [sellerId, navigate]);

    const handleLogout = () => {
        localStorage.removeItem("sellerId");
        localStorage.removeItem("sellerShopName");
        localStorage.removeItem("sellerStatus");
        navigate("/");
    };

    if (loading) return <p className="text-white text-center mt-10">Loading...</p>;
    if (error) return <p className="text-red-400 text-center mt-10">{error}</p>;

    return (
        <div className="min-h-screen bg-gray-900 text-white flex flex-col">
            <SellerNavbar />

            <main className="flex-1">


                <div className="max-w-2xl mx-auto p-6 bg-gray-800 rounded-lg mt-8 shadow-md">
                    <h2 className="text-2xl font-bold mb-4">Seller Profile</h2>

                    <div className="mb-4">
                        <strong>Shop Name:</strong> {seller?.shopName || localStorage.getItem("sellerShopName")}
                    </div>
                    <div className="mb-4">
                        <strong>Owner Name:</strong> {seller?.ownerName}
                    </div>
                    <div className="mb-4">
                        <strong>Email:</strong> {seller?.email}
                    </div>
                    <div className="mb-4">
                        <strong>Phone:</strong> {seller?.phone}
                    </div>
                    <div className="mb-4">
                        <strong>Address:</strong> {seller?.address}
                    </div>
                    <div className="mb-4">
                        <strong>Status:</strong> {seller?.status || localStorage.getItem("sellerStatus")}
                    </div>

                    <button
                        onClick={handleLogout}
                        className="mt-4 w-full bg-red-600 py-2 rounded-lg hover:bg-red-700 transition font-semibold"
                    >
                        Logout
                    </button>
                </div>
            </main>

            <Footer />
        </div>
    );
};

export default SellerProfile;
