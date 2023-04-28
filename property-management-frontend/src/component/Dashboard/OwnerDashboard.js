import React, { useContext, useEffect, useState } from "react";
import OffersTable from "../Tables/OffersTable";
import InquiriesTable from "../Tables/InquiriesTable";
import PropertyList from "../PropertyList/PropertyList";
import { Button, Typography, TextField, MenuItem } from "@mui/material";
import Filters from "../Filters";
import Pagination from "@mui/material/Pagination";
import AddIcon from "@mui/icons-material/Add";
import AddPropertyDialog from "../PropertyDetails/AddPropertyDialog";
import axiosInstance from "../../api/axiosInstance";
import UserContext from "../../context/UserContext";

const OwnerDashboard = () => {
  const [page, setPage] = useState(1);
  const [openAdd, setOpenAdd] = useState(false);
  const [properties, setProperties] = useState([]);
  const [filters, setFilters] = useState({
    city: "",
    bedroom: "",
    propertyType: "",
    maxPrice: "",
  });
  const [component, setComponent] = useState("properties");

  const {user} = useContext(UserContext);

  console.log(user);
  // useEffect(() => {
  //   axiosInstance.get("properties")
  //   .then(response => setProperties(response.data))
  //   .catch(error => console.error(error));
  // }, []);

  useEffect(() => {
    axiosInstance
      .get("properties/criteria", {
        params: filters,
      })
      .then((response) => setProperties(response.data))
      .catch((error) => console.error(error));
  }, [filters]);

  const handleChangePage = (event, value) => {
    setPage(value);
  };

  const handleAdd = () => {
    setOpenAdd(!openAdd);
  };

  const handleFilter = (city, numberOfRooms, propertyType, maxPrice) => {
    console.log({ city, numberOfRooms, propertyType, maxPrice });
    setFilters({ city, numberOfRooms, propertyType, maxPrice });
  };

  const components = [
    { value: "properties", label: "Listed Properties" },
    { value: "offers", label: "Offers" },
    { value: "inquiries", label: "Inquiries" },
  ];

  return (
    <div>
      <TextField
        style={{marginTop: 10, paddingTop: 10}}
        label="Lists"
        variant="outlined"
        fullWidth
        select
        value={component}
        onChange={(e) => setComponent(e.target.value)}
      >
        {components.map((option) => (
          <MenuItem key={option.value} value={option.value}>
            {option.label}
          </MenuItem>
        ))}
      </TextField>
      {component === "properties" ? (
        <>
          <Button startIcon={<AddIcon />} onClick={handleAdd}>
            {" "}
            Add property
          </Button>
          <AddPropertyDialog open={openAdd} toggle={handleAdd} />
          <Filters handleFilter={handleFilter} />
          <PropertyList properties={properties} />
          <div
            style={{ display: "flex", justifyContent: "center", margin: 10 }}
          >
            <Pagination count={10} page={page} onChange={handleChangePage} />
          </div>
        </>
      ) : null}
      {component === "offers" ? (
        <>
          <OffersTable />
          <div
            style={{ display: "flex", justifyContent: "center", margin: 10 }}
          >
            <Pagination count={10} page={page} onChange={handleChangePage} />
          </div>
        </>
      ) : null}
      {component === "inquiries" ? (
        <>
          <InquiriesTable />
          <div
            style={{ display: "flex", justifyContent: "center", margin: 10 }}
          >
            <Pagination count={10} page={page} onChange={handleChangePage} />
          </div>
        </>
      ) : null}
    </div>
  );
};

export default OwnerDashboard;
