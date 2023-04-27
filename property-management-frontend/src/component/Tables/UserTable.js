import React, { useState, useEffect } from 'react';
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import UserFormDialog from './UserFormDialog';

const UserTable = () => {
  const [users, setUsers] = useState([]);
  const [openFormDialog, setOpenFormDialog] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

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

  useEffect(() => {
    // Fetch users from backend or local storage and set them to the state
    const fetchedUsers = [
      { id: 1, firstName: 'John', lastName: 'Doe', email: 'johndoe@gmail.com', role: 'customer' , status: "inactive" },
      { id: 2, firstName: 'Jane', lastName: 'Doe', email: 'janedoe@gmail.com', role: 'owner', status: "active" },
      { id: 3, firstName: 'Bob', lastName: 'Smith', email: 'bobsmith@gmail.com', role: 'admin',status: "active" },
      // ...
    ];
    setUsers(fetchedUsers);
  }, []);

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