import React, { useContext } from 'react'
import UserContext from '../../context/UserContext'
import AdminDashboard from './AdminDashboard'
import OwnerDashboard from './OwnerDashboard';
import CustomerDashboard from './CustomerDashboard';
const Dashboard = () => {

  const {user} = useContext(UserContext);

  switch (user.role) {
    case 'admin':
      return <AdminDashboard/>
    case 'owner':
      return <OwnerDashboard/>
    default:
      return <CustomerDashboard/> 
  }

}

export default Dashboard