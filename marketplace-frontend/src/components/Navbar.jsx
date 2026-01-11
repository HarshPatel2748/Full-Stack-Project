import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-gray-900 text-white px-6 py-4 flex justify-between items-center">
      <div className="text-xl font-bold">
        <Link to="/home">Marketplace</Link>
      </div>
      <div className="space-x-6">
        <Link to="/home" className="hover:text-blue-400">Home</Link>
        <Link to="/cart" className="hover:text-blue-400">Cart</Link>
        <Link to="/user/profile" className="hover:text-blue-400">Profile</Link>
        <Link to="/" className="hover:text-blue-400">LogOut</Link>
      </div>
    </nav>
  );
};

export default Navbar;
