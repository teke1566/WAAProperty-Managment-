import React, { useContext, useEffect, useState } from "react";
import {
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
  Button,
  TextField,
  Grid,
  MenuItem,
} from "@mui/material";
import axiosInstance from "../../api/axiosInstance";
import { useNavigate } from "react-router";
import UserContext from "../../context/UserContext";
import { toast } from 'react-toastify';

const AddPropertyDialog = (props) => {
  const { property} = props;

  const { user } = useContext(UserContext);

  const [formData, setFormData] = useState({
    street: "",
    city: "",
    state: "",
    zipcode: "",
    price: "",
    bedrooms: "",
    bathrooms: "",
    listType: "",
    propertyType: "",
    imageUrl: "",
    propertyName: "",
    description: ""
  });

  useEffect(() => {
    if (property) {
      setFormData({
        street: property?.address?.street || "",
        city: property?.address?.city || "",
        state: property?.address?.state || "",
        zipcode: property?.address?.zipcode || "",
        price: property?.price || "",
        bedrooms: property?.numberOfRooms || "",
        bathrooms: property?.numberOfBathRooms || "",
        listType: property?.listType || "",
        propertyType: property?.propertyType || "",
        imageUrl: property?.imageUrl || "",
        propertyName: property?.propertyName || "",
        description: property?.description || "",
      });
    }
  }, [property]);

  const navigate = useNavigate();

  const propertyTypes = [
    { value: "", label: "None" },
    { value: "APARTMENT", label: "Apartment" },
    { value: "HOUSE", label: "House" },
    { value: "CONDO", label: "Condo" },
    { value: "TOWNHOUSE", label: "Townhouse" },
    { value: "DUPLEX", label: "Duplex" },
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
    { value: "sold", label: "Sold" },
  ];

  const bedrooms = [
    { value: "", label: "None" },
    { value: "1", label: "1 beds" },
    { value: "2", label: "2 beds" },
    { value: "3", label: "3 beds" },
    { value: "4", label: "4 beds" },
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
    // setFormData({
    //   address: "",
    //   price: "",
    //   bedrooms: "",
    //   bathrooms: "",
    // });
    if(property) {
      props.setProperty({
        ...property,
        address: {
          city: formData.city,
          state: formData.state,
          street: formData.street,
          zipcode: formData.zipcode,
        },
        price: formData.price,
        numberOfRooms: formData.bedrooms,
        numberOfBathRooms: formData.bathrooms,
        
      });
    } else {
      addProperty();
    }
    props.toggle();
  };

  const addProperty = () => {
    const newProperty = {
      address: {
        city: formData.city,
        state: formData.state,
        street: formData.street,
        zipcode: formData.zipcode,
      },
      imageUrl: formData.imageUrl,
      price: formData.price,
        numberOfRooms: formData.bedrooms,
        numberOfBathRooms: formData.bathrooms,
      propertyType: formData.propertyType,
      propertyName: formData.propertyName,
        description: formData.description,
        // users_id: user.id,
    };
    console.log("newProperty = ", newProperty);
    toast.success('Added property successfully!');

    // axiosInstance.post('/properties/owner',newProperty)
    // .then(response => navigate('/'))
    // .catch(error => console.error(error))

  }
  return (
    <Dialog open={props.open} onClose={props.toggle}>
      <DialogTitle>{property ? "Edit" : "New Property"}</DialogTitle>
      <DialogContent style={{ paddingTop: 20 }}>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <TextField
                name="street"
                label="Street"
                variant="outlined"
                fullWidth
                value={formData.street}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="city"
                label="City"
                variant="outlined"
                fullWidth
                value={formData.city}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="state"
                label="State"
                variant="outlined"
                fullWidth
                value={formData.state}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="zipcode"
                label="Zipcode"
                variant="outlined"
                fullWidth
                value={formData.zipcode}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                name="imageUrl"
                label="Property Image"
                variant="outlined"
                fullWidth
                value={formData.imageUrl}
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
                label="List Type"
                variant="outlined"
                name="listType"
                fullWidth
                select
                value={formData.listType}
                onChange={handleChange}
              >
                {listTypes.map((option) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={6}>
              <TextField
                label="Property Type"
                variant="outlined"
                name="propertyType"
                fullWidth
                select
                value={formData.propertyType}
                onChange={handleChange}
              >
                {propertyTypes.map((option) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="bedrooms"
                label="Bedrooms"
                variant="outlined"
                fullWidth
                select
                value={formData.bedrooms}
                onChange={handleChange}
              >
                {bedrooms.map((option) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="bathrooms"
                label="Bathrooms"
                variant="outlined"
                fullWidth
                select
                value={formData.bathrooms}
                onChange={handleChange}
              >
                {bedrooms.map((option) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={6}>
              <TextField
                name="propertyName"
                label="propertyName"
                variant="outlined"
                fullWidth
                value={formData.propertyName}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                name="description"
                label="description"
                variant="outlined"
                fullWidth
                value={formData.description}
                onChange={handleChange}
              />
            </Grid>
          </Grid>
        </form>
      </DialogContent>
      <DialogActions>
        <Button onClick={props.toggle} color="primary">
          Cancel
        </Button>
        <Button onClick={handleSubmit} variant="contained" color="primary">
          {property ? "Save" : "Add Property"}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AddPropertyDialog;
