import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import ProductCard from "../components/ProductCard";

const Homepage = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/api/products")
      .then((res) => res.json())
      .then((data) => {
        setProducts(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching products:", err);
        setLoading(false);
      });
  }, []);

  const handleAddToCart = (productId) => {
    console.log("Add to cart clicked for product:", productId);
    // cart API later
  };

  return (
    <div className="min-h-screen flex flex-col bg-gray-900 text-white">
      <Navbar />

      <main className="grow px-6 py-8 max-w-7xl mx-auto w-full">
        <div className="mb-8">
          <h1 className="text-3xl font-bold mb-2">
            Discover Products
          </h1>
          <p className="text-gray-400">
            Browse products from multiple sellers at one place
          </p>
        </div>

        {/* Search bar (frontend only for now) */}
        <div className="mb-10">
          <input
            type="text"
            placeholder="Search for products..."
            className="w-full md:w-1/2 bg-gray-800 text-white px-4 py-2 rounded-md outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        {/* Products */}
        {loading ? (
          <p className="text-gray-400">Loading products...</p>
        ) : products.length === 0 ? (
          <p className="text-gray-400">No products found</p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {products.map((product) => (
              <ProductCard
                key={product.id}
                product={product}
                onAddToCart={handleAddToCart}
              />
            ))}
          </div>
        )}
      </main>

      <Footer />
    </div>
  );
};

export default Homepage;
