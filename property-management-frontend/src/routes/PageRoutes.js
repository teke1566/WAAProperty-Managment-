import React from 'react';
import {Route, Routes} from 'react-router';
import Dashboard from '../component/Dashboard/Dashboard';
import LoginPage from '../container/Pages/LoginPage';
import RegisterPage from '../container/Pages/RegisterPage';
import ForgotPassword from '../container/Pages/ForgotPassword';
import PropertyDetails from '../component/PropertyDetails/PropertyDetails';

const PageRoutes = () => {
  return (
    <Routes>
        <Route path='/' element={<Dashboard/>}/>
        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>
        <Route path='/forgot-password' element={<ForgotPassword/>}/>
        <Route path='/properties/:id' element={<PropertyDetails/>}/>
    </Routes>
    )
}

export default PageRoutes