import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import axios from "axios";

const CheckoutPage = () => {
  const [cart, setCart] = useState([]);
  const [address, setAddress] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Get user info from localStorage
  const user = {
    id: Number(localStorage.getItem("userId")),
    firstName: localStorage.getItem("userFirstName") || "",
    lastName: localStorage.getItem("userLastName") || "",
    email: localStorage.getItem("userEmail") || ""
  };

  useEffect(() => {
    const savedCart = JSON.parse(localStorage.getItem("cart")) || [];
    setCart(savedCart);
    setAddress(localStorage.getItem("userAddress") || "");
  }, []);

  const totalAmount = cart.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  const handlePayment = async () => {
  try {
    if (!cart.length) return alert("Cart is empty");

    const userId = Number(localStorage.getItem("userId"));
    if (!userId) return alert("User not logged in");

    const productIds = cart.map(item => item.id);
    const quantities = cart.map(item => item.quantity);

    const orderDTO = {
      userId,
      productIds,
      quantities
    };

    // 1️⃣ Create order
    const orderRes = await axios.post("http://localhost:8080/api/orders", orderDTO);
    const order = orderRes.data;

    // 2️⃣ Create Razorpay payment
    const paymentRes = await axios.post("http://localhost:8080/api/payments/create", { orderId: order.id });
    const { razorpayOrderId, amount, currency } = paymentRes.data;

    // 3️⃣ Open Razorpay checkout
    const options = {
      key: import.meta.env.VITE_RAZORPAY_KEY,
      amount: amount * 100, 
      currency,
      name: "Marketplace",
      order_id: razorpayOrderId,
      handler: async function (response) {
        await axios.post("http://localhost:8080/api/payments/verify", response);
        alert("Payment Successful!");
        localStorage.removeItem("cart");
        navigate("/");
      },
    };
    const rzp = new window.Razorpay(options);
    rzp.open();

  } catch (err) {
    console.error(err.response || err);
    alert("Checkout failed: " + (err.response?.data?.message || err.message));
  }
};


  return (
    <div className="min-h-screen flex flex-col bg-gray-900 text-white">
      <Navbar />
      <main className="grow px-6 py-8 max-w-3xl mx-auto w-full">
        <h1 className="text-3xl font-bold mb-6">Checkout</h1>

        {cart.length === 0 ? (
          <p className="text-gray-400">Your cart is empty.</p>
        ) : (
          <div className="space-y-6">
            {cart.map((item) => (
              <div
                key={item.id}
                className="flex items-center gap-4 bg-gray-800 p-4 rounded-xl shadow-md"
              >
                <img
                  src={item.imageUrl || "https://via.placeholder.com/100x120"}
                  alt={item.name}
                  className="w-24 h-28 object-cover rounded"
                />
                <div className="flex-1">
                  <h3 className="font-semibold text-lg">{item.name}</h3>
                  <p className="text-gray-400 text-sm mt-1 line-clamp-2">
                    {item.description || "No description available."}
                  </p>
                  <p className="text-indigo-400 font-bold mt-2">
                    ${item.price} x {item.quantity}
                  </p>
                </div>
              </div>
            ))}

            <div className="mt-6">
              <label className="block mb-2 font-semibold">Delivery Address</label>
              <textarea
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                className="w-full px-4 py-2 bg-gray-700 rounded-lg text-white"
                placeholder="Enter delivery address"
              />
            </div>

            <div className="mt-6 flex justify-between items-center">
              <p className="text-xl font-bold">Total: ${totalAmount.toFixed(2)}</p>
              <button
                onClick={handlePayment}
                disabled={loading}
                className="bg-green-600 px-6 py-2 rounded-lg hover:bg-green-700 transition disabled:opacity-50"
              >
                {loading ? "Processing..." : "Pay Now"}
              </button>
            </div>
            {error && <p className="text-red-400 mt-3">{error}</p>}
          </div>
        )}
      </main>
      <Footer />
    </div>
  );
};

export default CheckoutPage;
