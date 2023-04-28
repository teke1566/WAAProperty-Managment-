import React, { useState, useEffect } from 'react';
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import UserFormDialog from './UserFormDialog';
import axiosInstance from '../../api/axiosInstance';

const UserTable = () => {
  const [users, setUsers] = useState([]);
  const [openFormDialog, setOpenFormDialog] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    axiosInstance.get('/admin/customer/1')
    .then(response => {
      setUsers(response.data);
      console.log(response.data);
    })
    .catch(error => console.error(error))
    
  }, []);

  const handleOpenFormDialog = (user) => {
    setSelectedUser(user);
    setOpenFormDialog(true);
  };

  const handleCloseFormDialog = () => {
    setSelectedUser(null);
    setOpenFormDialog(false);
  };

  const handleAddUser = (user) => {
    setUsers([...users, user]);
    handleCloseFormDialog();
  };

  const handleUpdateUser = (user) => {
    const updatedUsers = users.map(u => u.id === user.id ? user : u);
    setUsers(updatedUsers);
    handleCloseFormDialog();
  };

  const handleDeleteUser = (id) => {
    
    // eslint-disable-next-line no-restricted-globals
    if(confirm("Delete user ?")) {
        const filteredUsers = users.filter(u => u.id !== id);
        setUsers(filteredUsers);
    }
  };



  return (
    <div>
      {/* <Button variant="contained" color="primary" startIcon={<AddIcon />} onClick={() => handleOpenFormDialog(null)}>
        Add User
      </Button> */}
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>First Name</TableCell>
              <TableCell>Last Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Role</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users.map(user => (
              <TableRow key={user.id}>
                <TableCell>{user.id}</TableCell>
                <TableCell>{user.firstName}</TableCell>
                <TableCell>{user.lastName}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.role}</TableCell>
                <TableCell>
                  <Button variant="contained" style={{backgroundColor: 'green', marginRight: 10}} startIcon={<EditIcon />} onClick={() => handleOpenFormDialog(user)}>
                    {user.status === 'active' ? "Deactivate": "Activate"}
                  </Button>
                  <Button variant="contained" color="primary" style={{marginRight: 10}} startIcon={<EditIcon />} onClick={() => handleOpenFormDialog(user)}>
                    Reset password
                  </Button>
                  <Button variant="contained" color="secondary" startIcon={<DeleteIcon />} onClick={() => handleDeleteUser(user.id)}>
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <UserFormDialog
        open={openFormDialog}
        onClose={handleCloseFormDialog}
        onAddUser={handleAddUser}
        onUpdateUser={handleUpdateUser}
        user={selectedUser}
      />
    </div>
  );
};

export default UserTable;