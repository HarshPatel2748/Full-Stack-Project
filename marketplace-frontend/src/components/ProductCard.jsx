import React from "react";

const ProductCard = ({ product, onAddToCart }) => {
  return (
    <div className="bg-gray-800 rounded-lg p-4 hover:shadow-lg transition">
      <img
        src={product.imageUrl || "https://via.placeholder.com/300x200"}
        alt={product.name}
        className="rounded-md mb-4 h-40 w-full object-cover"
      />

      <h2 className="font-semibold text-lg">{product.name}</h2>

      <p className="text-gray-400 text-sm mb-1">
        by {product.sellerShopName}
      </p>

      <p className="text-blue-400 font-bold mb-4">
        ₹{product.price}
      </p>

      <button
        onClick={() => onAddToCart(product.id)}
        className="w-full bg-blue-600 hover:bg-blue-500 py-2 rounded-md"
      >
        Add to Cart
      </button>
    </div>
  );
};

export default ProductCard;
