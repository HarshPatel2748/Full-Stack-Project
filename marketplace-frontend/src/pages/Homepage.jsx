import React from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const Homepage = () => {
  return (
    <div className="flex flex-col min-h-screen bg-gray-900 text-white">
      <Navbar />

      <main className="grow px-6 py-10">
        <h1 className="text-4xl font-bold mb-6">Welcome to the Marketplace</h1>
        <p className="mb-6 text-gray-300">
          Explore a wide variety of products from multiple sellers.
        </p>

        <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {/* Example Product Cards */}
          <div className="bg-gray-800 p-4 rounded-lg hover:shadow-lg transition">
            <img src="https://via.placeholder.com/150" alt="Product" className="mb-4 rounded" />
            <h2 className="font-semibold">Product 1</h2>
            <p className="text-gray-400">$99.99</p>
            <button className="mt-2 bg-blue-600 hover:bg-blue-500 px-4 py-1 rounded">Add to Cart</button>
          </div>

          <div className="bg-gray-800 p-4 rounded-lg hover:shadow-lg transition">
            <img src="https://via.placeholder.com/150" alt="Product" className="mb-4 rounded" />
            <h2 className="font-semibold">Product 2</h2>
            <p className="text-gray-400">$49.99</p>
            <button className="mt-2 bg-blue-600 hover:bg-blue-500 px-4 py-1 rounded">Add to Cart</button>
          </div>

          <div className="bg-gray-800 p-4 rounded-lg hover:shadow-lg transition">
            <img src="https://via.placeholder.com/150" alt="Product" className="mb-4 rounded" />
            <h2 className="font-semibold">Product 3</h2>
            <p className="text-gray-400">$29.99</p>
            <button className="mt-2 bg-blue-600 hover:bg-blue-500 px-4 py-1 rounded">Add to Cart</button>
          </div>
        </div>
      </main>

      <Footer />
    </div>
  );
};

export default Homepage;
