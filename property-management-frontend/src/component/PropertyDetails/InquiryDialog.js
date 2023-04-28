import React, { useState } from 'react'
import {
    Dialog,
    DialogTitle,
    DialogActions,
    DialogContent,
    Button,
    TextField,
  } from "@mui/material";
  import { toast } from 'react-toastify';

const InquiryDialog = (props) => {
    const [inquiry, setinquiry] = useState('');

    const handleinquiryChange = (event) => {
      setinquiry(event.target.value);
    };
  
    const handleMakeinquiry = () => {
      console.log(`Making inquiry of $${inquiry}`);
      props.toggle();
      setinquiry("");
      toast.success('Inquiry sent successfully!');

    };
  
    return (
      <Dialog open={props.open} onClose={props.toggle}>
        <DialogTitle>Make an inquiry</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="inquiry"
            label="inquiry"
            value={inquiry}
            onChange={handleinquiryChange}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={props.toggle} color="primary">
            Cancel
          </Button>
          <Button onClick={handleMakeinquiry} color="primary">
            Send
          </Button>
        </DialogActions>
      </Dialog>
    );
}

export default InquiryDialog