import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import SellerNavbar from "../../components/SellerNavbar";
import Footer from "../../components/Footer";

const SellerEditProduct = () => {
  const { productId } = useParams();
  const navigate = useNavigate();

  const seller = JSON.parse(localStorage.getItem("seller"));
  const sellerId = seller?.sellerId;

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    imageUrl: "",
    categoryId: ""
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // 🔹 Fetch existing product
  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8080/api/products/seller/${sellerId}`
        );

        const product = res.data.find(p => p.id === Number(productId));

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
        setError("Failed to load product");
      }
    };

    fetchProduct();
  }, [productId, sellerId]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // 🔹 THIS IS THE MOST IMPORTANT PART
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const payload = {
        name: formData.name,
        description: formData.description,
        price: Number(formData.price),
        stock: Number(formData.stock),
        imageUrl: formData.imageUrl,
        categoryId: formData.categoryId
          ? Number(formData.categoryId)
          : null
      };

      await axios.put(
        `http://localhost:8080/api/products/seller/${sellerId}/${productId}`,
        payload
      );

      navigate("/seller/dashboard");
    } catch (err) {
      console.error(err.response);
      setError("Failed to update product");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col">
      <SellerNavbar />
      <main className="flex-1">

      <div className="max-w-xl mx-auto p-6">
        <h2 className="text-2xl font-bold mb-4">Edit Product</h2>

        {error && <p className="text-red-400 mb-3">{error}</p>}

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
            name="price"
            type="number"
            value={formData.price}
            onChange={handleChange}
            placeholder="Price"
            className="w-full px-4 py-2 bg-gray-700 rounded"
            required
          />

          <input
            name="stock"
            type="number"
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
            name="categoryId"
            type="number"
            value={formData.categoryId}
            onChange={handleChange}
            placeholder="Category ID"
            className="w-full px-4 py-2 bg-gray-700 rounded"
          />

          <button
            type="submit"
            disabled={loading}
            className="bg-indigo-600 px-6 py-2 rounded hover:bg-indigo-700"
          >
            {loading ? "Updating..." : "Update Product"}
          </button>
        </form>
      </div>
      </main>

      <Footer />
    </div>
  );
};

export default SellerEditProduct;
