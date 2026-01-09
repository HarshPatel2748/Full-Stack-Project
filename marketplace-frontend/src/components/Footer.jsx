import React from 'react';

const Footer = () => {
  return (
    <footer className="bg-gray-800 text-gray-400 py-6 mt-10 text-center text-sm">
      <div>© 2026 Harsh Patel</div>
      <div className="mt-2">
        Multi-Vendor Marketplace | Built with React, Spring Boot, MySQL, Tailwind CSS
      </div>
      <div className="mt-2">
        Contact: <a href="mailto:patelharsh4816@gmail.com" className="text-blue-400 hover:underline">patelharsh4816@gmail.com</a>
      </div>
      <div className="mt-1">
        <a href="https://github.com/HarshPatel4816" className="text-blue-400 hover:underline mr-2">GitHub</a>
        <a href="https://www.linkedin.com/in/harsh-patel-1467252b4/" className="text-blue-400 hover:underline">LinkedIn</a>
      </div>
    </footer>
  );
};

export default Footer;
