import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-gray-900 text-white px-6 py-4 flex justify-between items-center">
      <div className="text-xl font-bold">
        Marketplace
      </div>
      <div className="space-x-6">
        <Link to="/profile" className="hover:text-blue-400">Profile</Link>
        <Link to="/" className="hover:text-blue-400">LogOut</Link>
      </div>
    </nav>
  );
};

export default Navbar;
