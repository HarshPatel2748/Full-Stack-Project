import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import SellerNavbar from "../../components/SellerNavbar";
import Footer from "../../components/Footer";

const SellerEditProduct = () => {
  const { productId } = useParams();
  const navigate = useNavigate();

  // ✅ Correct sellerId source (NO JSON.parse)
  const sellerId = Number(localStorage.getItem("sellerId"));

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    imageUrl: "",
    categoryId: ""
  });

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // 🔐 Redirect if seller not logged in
  useEffect(() => {
    if (!sellerId) {
      navigate("/login/seller");
    }
  }, [sellerId, navigate]);

  // 🔹 Fetch product by seller
  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8080/api/products/seller/${sellerId}`
        );

        const product = res.data.find(
          (p) => p.id === Number(productId)
        );

        if (!product) {
          setError("Product not found");
          return;
        }

        setFormData({
          name: product.name || "",
          description: product.description || "",
          price: product.price || "",
          stock: product.stock || "",
          imageUrl: product.imageUrl || "",
          categoryId: product.category?.id || ""
        });
      } catch (err) {
        console.error(err);
        setError("Failed to load product");
      } finally {
        setLoading(false);
      }
    };

    if (sellerId && productId) {
      fetchProduct();
    }
  }, [sellerId, productId]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // 🔹 UPDATE PRODUCT (backend-aligned)
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const payload = {
        name: formData.name,
        description: formData.description,
        price: Number(formData.price),
        stock: Number(formData.stock),
        imageUrl: formData.imageUrl,
        categoryId: Number(formData.categoryId) // ✅ REQUIRED
      };

      console.log("Updating product:", payload);

      await axios.put(
        `http://localhost:8080/api/products/seller/${sellerId}/${productId}`,
        payload
      );

      navigate("/seller/dashboard");
    } catch (err) {
      console.error(err.response?.data);
      setError(err.response?.data?.message || "Failed to update product");
    }
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col">
      <SellerNavbar />

      <main className="flex-1">
        <div className="max-w-xl mx-auto p-6">
          <h2 className="text-2xl font-bold mb-4">Edit Product</h2>

          {loading && <p className="text-gray-400">Loading...</p>}
          {error && <p className="text-red-400 mb-4">{error}</p>}

          {!loading && !error && (
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Product Name"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                required
              />

              <textarea
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Description"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                required
              />

              <input
                type="number"
                name="price"
                value={formData.price}
                onChange={handleChange}
                placeholder="Price"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                required
              />

              <input
                type="number"
                name="stock"
                value={formData.stock}
                onChange={handleChange}
                placeholder="Stock"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                required
              />

              <input
                name="imageUrl"
                value={formData.imageUrl}
                onChange={handleChange}
                placeholder="Image URL"
                className="w-full px-4 py-2 bg-gray-700 rounded"
              />

              <input
                type="number"
                name="categoryId"
                value={formData.categoryId}
                onChange={handleChange}
                placeholder="Category ID"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                required
              />

              <button
                type="submit"
                className="bg-indigo-600 px-6 py-2 rounded hover:bg-indigo-700"
              >
                Update Product
              </button>
            </form>
          )}
        </div>
      </main>

      <Footer />
    </div>
  );
};

export default SellerEditProduct;
