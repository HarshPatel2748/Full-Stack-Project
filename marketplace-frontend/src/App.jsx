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
      </Routes>
    </Router>
  )
}

export default App
