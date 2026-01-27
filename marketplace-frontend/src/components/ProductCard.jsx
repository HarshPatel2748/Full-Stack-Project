import React from "react";

const ProductCard = ({ product, onAddToCart }) => {
  return (
    <div className="bg-gray-800 p-4 rounded-lg shadow-md flex flex-col">
      <img
        src={product.imageUrl || "https://via.placeholder.com/150"}
        alt={product.name}
        className="w-full h-40 object-cover rounded-md mb-2"
      />
      <h3 className="text-lg font-semibold">{product.name}</h3>
      <p className="text-gray-300 text-sm">{product.description}</p>
      <p className="mt-2 font-bold">${product.price}</p>
      <button
        onClick={onAddToCart}
        className="mt-2 bg-indigo-600 py-1 px-2 rounded hover:bg-indigo-700 transition"
      >
        Add to Cart
      </button>
    </div>
  );
};

export default ProductCard;
