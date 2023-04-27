import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
  Button,
  TextField,
} from "@mui/material";

const OfferDialog = (props) => {
  const [offer, setOffer] = useState(0);

  const handleOfferChange = (event) => {
    setOffer(event.target.value);
  };

  const handleMakeOffer = () => {
    console.log(`Making offer of $${offer}`);
    props.toggle();
    setOffer(0);
  };

  return (
    <Dialog open={props.open} onClose={props.toggle}>
      <DialogTitle>Make an Offer</DialogTitle>
      <DialogContent>
        <TextField
          autoFocus
          margin="dense"
          id="offer"
          label="Offer Amount ($)"
          type="number"
          value={offer}
          onChange={handleOfferChange}
          fullWidth
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={props.toggle} color="primary">
          Cancel
        </Button>
        <Button onClick={handleMakeOffer} color="primary">
          Submit
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default OfferDialog;
