import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UserSignup from './pages/auth/user/UserSignup.jsx'
import './App.css'
import UserLogin from './pages/auth/user/UserLogin.jsx'
import SellerLogin from './pages/auth/seller/SellerLogin.jsx'
import SellerSignup from './pages/auth/seller/SellerSignup.jsx'
import AdminLogin from './pages/auth/admin/AdminLogin.jsx'
import WelcomePage from './pages/WelcomePage.jsx'
import Homepage from './pages/Homepage.jsx'
import AdminDashboard from './pages/admin/AdminDashboard.jsx'
import SellerDashboard from './pages/seller/SellerDashboard.jsx'
import SellerProductForm from './pages/seller/SellerProductForm.jsx'
import SellerEditProduct from './pages/seller/SellerEditProduct.jsx'
import SellerProfile from './pages/seller/SellerProfile.jsx'
import UserProfile from './pages/user/UserProfile.jsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path='' element={<WelcomePage />} />

        <Route path="/signup/user" element={<UserSignup />} />
        <Route path="/login/user" element={<UserLogin />} />
        <Route path='/signup/seller' element={<SellerSignup />} />
        <Route path='/login/seller' element={<SellerLogin />} />
        <Route path='/login/admin' element={<AdminLogin />} />

        <Route path='/home' element={<Homepage />} />

        <Route path='/admin/dashboard' element={<AdminDashboard />} />
        <Route path='/seller/dashboard' element={<SellerDashboard />} />
        <Route path="/seller/product/:productId" element={<SellerProductForm />} />
        <Route path="/seller/add-product" element={<SellerProductForm />} />
        <Route path="/seller/edit-product/:productId" element={<SellerEditProduct />} />
        <Route path='/seller/profile' element={<SellerProfile />} />
        <Route path='/user/profile' element={<UserProfile />} />

      </Routes>
    </Router>
  )
}

export default App
