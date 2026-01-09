import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import UserSignup from './pages/UserSignup.jsx'
import './App.css'
import UserLogin from './pages/UserLogin.jsx'
import SellerLogin from './pages/SellerLogin.jsx'
import SellerSignup from './pages/SellerSignup.jsx'
import AdminLogin from './pages/AdminLogin.jsx'
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
