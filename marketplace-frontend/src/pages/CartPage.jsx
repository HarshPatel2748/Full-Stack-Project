import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const CartPage = () => {
  const [cart, setCart] = useState([]);

  useEffect(() => {
    const savedCart = JSON.parse(localStorage.getItem("cart")) || [];
    setCart(savedCart);
  }, []);

  const handleQuantityChange = (productId, delta) => {
    const updated = cart.map((item) => {
      if (item.id === productId) {
        return { ...item, quantity: Math.max(1, item.quantity + delta) };
      }
      return item;
    });
    setCart(updated);
    localStorage.setItem("cart", JSON.stringify(updated));
  };

  const handleRemove = (productId) => {
    const updated = cart.filter((item) => item.id !== productId);
    setCart(updated);
    localStorage.setItem("cart", JSON.stringify(updated));
  };

  const total = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);

  return (
    <div className="min-h-screen flex flex-col bg-gray-900 text-white">
      <Navbar />

      <main className="grow px-6 py-8 max-w-5xl mx-auto w-full">
        <h1 className="text-3xl font-bold mb-6">Your Cart</h1>

        {cart.length === 0 ? (
          <p className="text-gray-400">Your cart is empty.</p>
        ) : (
          <div className="space-y-6">
            {cart.map((item) => (
              <div
                key={item.id}
                className="flex flex-col sm:flex-row items-center sm:items-start gap-4 bg-gray-800 p-4 rounded-2xl shadow-md"
              >
                {/* Image */}
                <img
                  src={item.imageUrl || "https://via.placeholder.com/150x200"}
                  alt={item.name}
                  className="w-32 h-40 object-cover rounded-lg shrink-0"
                />

                {/* Details */}
                <div className="flex-1 flex flex-col sm:flex-row sm:justify-between items-start sm:items-center w-full gap-4">
                  <div className="flex-1">
                    <h3 className="font-semibold text-lg">{item.name}</h3>
                    <p className="text-gray-400 text-sm mt-1 line-clamp-2">
                      {item.description || "No description available."}
                    </p>
                    <p className="text-indigo-400 font-bold mt-2">${item.price}</p>
                  </div>

                  {/* Quantity + Remove */}
                  <div className="flex items-center gap-2 mt-3 sm:mt-0">
                    <button
                      onClick={() => handleQuantityChange(item.id, -1)}
                      className="bg-gray-700 px-3 py-1 rounded hover:bg-gray-600 transition"
                    >
                      -
                    </button>
                    <span className="px-2">{item.quantity}</span>
                    <button
                      onClick={() => handleQuantityChange(item.id, 1)}
                      className="bg-gray-700 px-3 py-1 rounded hover:bg-gray-600 transition"
                    >
                      +
                    </button>
                    <button
                      onClick={() => handleRemove(item.id)}
                      className="bg-red-600 px-3 py-1 rounded hover:bg-red-700 transition ml-2"
                    >
                      Remove
                    </button>
                  </div>
                </div>
              </div>
            ))}

            {/* Total + Checkout */}
            <div className="mt-6 flex justify-end flex-col sm:flex-row sm:items-center gap-4">
              <p className="text-xl font-bold">Total: ${total.toFixed(2)}</p>
              <button
                onClick={() => window.location.href = "/checkout"}
                className="bg-green-600 px-5 py-2 rounded-lg hover:bg-green-700 transition"
              >
                Checkout
              </button>


            </div>
          </div>
        )}
      </main>

      <Footer />
    </div>
  );
};

export default CartPage;
