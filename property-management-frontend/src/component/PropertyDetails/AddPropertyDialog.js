import React, { useState } from 'react'
import {
    Dialog,
    DialogTitle,
    DialogActions,
    DialogContent,
    Button,
    TextField,
    Grid,
  } from "@mui/material";

const AddPropertyDialog = (props) => {
  const [property, setProperty] = useState(null);
  const [formData, setFormData] = useState({
    address: '',
    price: '',
    bedrooms: '',
    bathrooms: '',
  });

  const propertyTypes = [
    { value: "", label: "None" },
    { value: "apartment", label: "Apartment" },
    { value: "house", label: "House" },
    { value: "condo", label: "Condo" },
  ];
  
  const homeTypes = [
    { value: "", label: "None" },
    { value: "single-family", label: "Single Family" },
    { value: "multi-family", label: "Multi Family" },
    { value: "townhouse", label: "Townhouse" },
  ];
  
  const listTypes = [
      { value: "", label: "None" },
      { value: "sale", label: "For Sale" },
      { value: "rent", label: "For Rent" },
  ];
  
  const bedrooms = [
      { value: "", label: "None" },
      { value: "1", label: "1 beds" },
      { value: "2", label: "2 beds" },
      { value: "3", label: "3 beds" },
      { value: "4", label: "4 beds" },
  ];
  
  const bathrooms = [
      { value: "", label: "None" },
      { value: "1", label: "1 baths" },
      { value: "2", label: "2 baths" },
      { value: "3", label: "3 baths" },
      { value: "4", label: "4 baths" },
  ];

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(formData);
    setFormData({
      address: '',
      price: '',
      bedrooms: '',
      bathrooms: '',
    });
  };

  return (
    <Dialog open={props.open} onClose={props.toggle}>
        <DialogTitle>New Property</DialogTitle>
        <DialogContent>
        <form onSubmit={handleSubmit}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            name="address"
            label="Address"
            variant="outlined"
            fullWidth
            value={formData.address}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            name="price"
            label="Price"
            variant="outlined"
            fullWidth
            value={formData.price}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            name="bedrooms"
            label="Bedrooms"
            variant="outlined"
            fullWidth
            value={formData.bedrooms}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            name="bathrooms"
            label="Bathrooms"
            variant="outlined"
            fullWidth
            value={formData.bathrooms}
            onChange={handleChange}
          />
        </Grid>
        <Grid item xs={12}>
          
        </Grid>
      </Grid>
    </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={props.toggle} color="primary">
            Cancel
          </Button>
          <Button type="submit" variant="contained" color="primary">
            Add Property
          </Button>
        </DialogActions>
      </Dialog>
  )
}

export default AddPropertyDialog