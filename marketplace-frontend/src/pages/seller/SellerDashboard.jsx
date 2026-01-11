import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import SellerNavbar from "../../components/SellerNavbar";
import Footer from "../../components/Footer";

const SellerDashboard = () => {
  const navigate = useNavigate();

  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // ✅ Get seller from localStorage
  const seller = JSON.parse(localStorage.getItem("seller"));
  const sellerId = seller?.sellerId;

  useEffect(() => {
    if (!sellerId) {
      navigate("/login/seller");
      return;
    }

    fetchProducts();
  }, [sellerId]);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const res = await axios.get(
        `http://localhost:8080/api/products/seller/${sellerId}`
      );
      setProducts(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      console.error(err);
      setError("Failed to fetch products");
    } finally {
      setLoading(false);
    }
  };

  // 🗑 DELETE PRODUCT
  const handleDelete = async (productId) => {
    const confirm = window.confirm(
      "Are you sure you want to delete this product?"
    );
    if (!confirm) return;

    try {
      await axios.delete(
        `http://localhost:8080/api/products/seller/${sellerId}/${productId}`
      );

      // remove from UI instantly
      setProducts((prev) =>
        prev.filter((product) => product.id !== productId)
      );
    } catch (err) {
      alert("Failed to delete product");
    }
  };

  // ✏ UPDATE PRODUCT
  const handleUpdate = (productId) => {
    navigate(`/seller/edit-product/${productId}`);
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col">
      <SellerNavbar />
      <main className="flex-1">

        <div className="flex justify-between items-center p-6">
          <h2 className="text-2xl font-bold">My Products</h2>
          <button
            onClick={() => navigate("/seller/add-product")}
            className="bg-indigo-600 px-4 py-2 rounded-lg hover:bg-indigo-700 transition"
          >
            + Add Product
          </button>
        </div>

        <div className="p-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 place-items-center">
          {loading && <p>Loading products...</p>}
          {error && <p className="text-red-400">{error}</p>}
          {!loading && products.length === 0 && <p>No products found</p>}

          {products.map((product) => (
            <div
              key={product.id}
              className="bg-gray-800 p-4 rounded-lg shadow-md w-full max-w-[260px]"
            >
              <img
                src={product.imageUrl || "https://via.placeholder.com/300"}
                alt={product.name}
                className="w-full h-40 object-cover rounded-md mb-3"
              />

              <h3 className="text-lg font-semibold">{product.name}</h3>
              <p className="text-gray-300 text-sm">{product.description}</p>

              <div className="mt-2 flex justify-between text-sm">
                <span className="font-bold">₹{product.price}</span>
                <span className="text-gray-400">
                  Stock: {product.stock}
                </span>
              </div>

              {/* ACTION BUTTONS */}
              <div className="flex gap-2 mt-4">
                <button
                  onClick={() => handleUpdate(product.id)}
                  className="flex-1 bg-yellow-500 text-black py-1 rounded hover:bg-yellow-600"
                >
                  Edit
                </button>

                <button
                  onClick={() => handleDelete(product.id)}
                  className="flex-1 bg-red-600 py-1 rounded hover:bg-red-700"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </main>

      <Footer />
    </div>
  );
};

export default SellerDashboard;
