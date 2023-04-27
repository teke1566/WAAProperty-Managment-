import React from 'react'
import {
    Dialog,
    DialogTitle,
    DialogActions,
    DialogContent,
    Button,
    Typography,
  } from "@mui/material";

const DeleteDialog = (props) => {
  return (
    <Dialog open={props.open} onClose={props.toggle}>
        <DialogTitle>Delete Property</DialogTitle>
        <DialogContent>
          <Typography>Are you sure ?</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={props.toggle} color="primary">
            Cancel
          </Button>
          <Button onClick={props.handleDelete} variant="contained" color="primary">
            Delete
          </Button>
        </DialogActions>
      </Dialog>
  )
}

export default DeleteDialog