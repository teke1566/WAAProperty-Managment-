import React, { useEffect, useState } from "react";
import {
  MenuItem,
  TextField,
  InputAdornment,
  IconButton,
  Grid,
} from "@mui/material";
import { Search } from "@mui/icons-material";

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

const Filters = (props) => {
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [propertyType, setPropertyType] = useState("");
  const [listType, setListType] = useState("");
  const [homeType, setHomeType] = useState("");
  const [location, setLocation] = useState("");
  const [bedroom, setbedroom] = useState("");
  const [bathroom, setbathroom] = useState("");
  const [flag, setFlag] = useState(true);

  useEffect(() => {
    props.handleFilter(location, bedroom, propertyType, maxPrice);
  }, [flag,bedroom, propertyType, maxPrice]);

  const handleSearch = () => {
    setFlag(!flag);
  };


  return (
    <Grid container spacing={1} style={{margin: 2}} maxWidth="xl">
      <Grid item xs={2} sm={2} md={2}>
        <TextField
          label="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton onClick={handleSearch}>
                  <Search />
                </IconButton>
              </InputAdornment>
            ),
          }}
        />
      </Grid>
      <Grid item xs={2} sm={2} md={2}>
        <TextField
          label="List Type"
          variant="outlined"
          fullWidth
          select
          value={listType}
          onChange={(e) => setListType(e.target.value)}
        >
          {listTypes.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
      <Grid item xs={2} sm={2} md={2}>
        <TextField
          label="Property Type"
          variant="outlined"
          fullWidth
          select
          value={propertyType}
          onChange={(e) => {
            setPropertyType(e.target.value);
          }}
        >
          {propertyTypes.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
      <Grid item xs={1} sm={1} md={1}>
        <TextField
          label="Bedrooms"
          variant="outlined"
          fullWidth
          select
          value={bedroom}
          onChange={(e) => {
            setbedroom(e.target.value);
          }}
        >
            {bedrooms.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
      <Grid item xs={1} sm={1} md={1}>
        <TextField
          label="Bathrooms"
          variant="outlined"
          fullWidth
          select
          value={bathroom}
          onChange={(e) => setbathroom(e.target.value)}
        >
            {bathrooms.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
      <Grid item xs={2} sm={2} md={2}>
        <TextField
          label="Home Type"
          variant="outlined"
          fullWidth
          select
          value={homeType}
          onChange={(e) => setHomeType(e.target.value)}
        >
          {homeTypes.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </Grid>
      <Grid item xs={1} sm={1} md={1}>
        <TextField
          label="Min Price"
          variant="outlined"
          fullWidth
          type="number"
          value={minPrice}
          onChange={(e) => setMinPrice(e.target.value)}
          InputProps={{
            startAdornment: <InputAdornment position="start">$</InputAdornment>,
          }}
        />
      </Grid>
      <Grid item xs={1} sm={1} md={1}>
        <TextField
          label="Max Price"
          variant="outlined"
          fullWidth
          type="number"
          value={maxPrice}
          onChange={(e) => {
            setMaxPrice(e.target.value);
          }}
          InputProps={{
            startAdornment: <InputAdornment position="start">$</InputAdornment>,
          }}
        />
      </Grid>
    </Grid>
  );
};

export default Filters;
