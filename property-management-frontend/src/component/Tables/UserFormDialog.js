import React, { useState } from 'react';
import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
} from '@mui/material';

function UserFormDialog(props) {
  const [user, setUser] = useState(props.user || { id: '', name: '', email: '' });

  const handleClose = () => {
    props.onClose();
  };

  const handleSave = () => {
    props.onUpdateUser(user);
    handleClose();
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setUser((prevUser) => ({
      ...prevUser,
      [name]: value,
    }));
  };

  return (
    <Dialog open={props.open} onClose={handleClose}>
      <DialogTitle>{props.title}</DialogTitle>
      <DialogContent>
        <TextField
          name="name"
          label="Name"
          value={user.name}
          onChange={handleInputChange}
          fullWidth
          required
        />
        <TextField
          name="email"
          label="Email"
          value={user.email}
          onChange={handleInputChange}
          type="email"
          fullWidth
          required
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>Cancel</Button>
        <Button onClick={handleSave} color="primary">Save</Button>
      </DialogActions>
    </Dialog>
  );
}

export default UserFormDialog;
