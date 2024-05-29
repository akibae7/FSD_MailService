
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './Pages/Header/Header';
import Dashboard from './Pages/Dashboard/Dashboard';
import NoMatch from './Pages/noMatch/NoMacth';
import PostUser from './Pages/employee/PostUser';
import Login from './Pages/Login';
import Register from './Pages/Register';
import Footer from './Pages/Footer/Footer';
import UpdateUser from './Pages/employee/UpdateUser';

const App = () => {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/employee" element={<PostUser/>} />
        <Route path="/employee/:id" element={<UpdateUser/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<NoMatch />} />
      </Routes>
      <Footer />
      

    </>
  );
};

export default App;
