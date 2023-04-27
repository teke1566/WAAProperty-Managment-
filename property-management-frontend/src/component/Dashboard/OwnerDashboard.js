import React, { useEffect, useState } from 'react'
import OffersTable from '../Tables/OffersTable';
import PropertyList from '../PropertyList/PropertyList';
import { Button, Typography } from '@mui/material';
import Filters from '../Filters'
import Pagination from "@mui/material/Pagination";
import AddIcon from '@mui/icons-material/Add';
import AddPropertyDialog from '../PropertyDetails/AddPropertyDialog';
import axiosInstance from "../../api/axiosInstance"

const OwnerDashboard = () => {
  const [page, setPage] = useState(1);
  const [openAdd, setOpenAdd] = useState(false);
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    axiosInstance.get("properties")
    .then(response => setProperties(response.data))
    .catch(error => console.error(error));
  }, []);
  const handleChangePage = (event, value) => {
    setPage(value);
  };

  const offers = [
    {
      id: 1,
      property: properties[1],
      value: 1400,
      status: "pending",
      customer: "John Doe",
      buyerEmail: "johndoe@example.com",
    },
    {
      id: 2,
      property: properties[3],
      value: 2900,
      status: "accepted",
      customer: "Jane Smith",
      buyerEmail: "janesmith@example.com",
    },
    {
      id: 3,
      property: properties[2],
      value: 1100,
      status: "rejected",
      customer: "Bob Johnson",
      buyerEmail: "bobjohnson@example.com",
    },
    {
      id: 4,
      property: properties[4],
      value: 1700,
      status: "pending",
      customer: "Emily Davis",
      buyerEmail: "emilydavis@example.com",
    },
    {
      id: 5,
      property: properties[5],
      value: 950,
      status: "pending",
      customer: "Mike Wilson",
      buyerEmail: "mikewilson@example.com",
    },
    {
      id: 6,
      property: properties[6],
      value: 1400,
      status: "rejected",
      customer: "Susan Brown",
      buyerEmail: "susanbrown@example.com",
    },
  ];

  const handleAdd = () => {
    setOpenAdd(!openAdd);
  };

  return (
    <div>
      <Typography fontSize={30}>Listed Properties</Typography>
      <Button startIcon={<AddIcon/>} onClick={handleAdd}> Add property</Button>
      <AddPropertyDialog open={openAdd} toggle={handleAdd}/>
      <Filters />
      <PropertyList properties={properties}/>
      <div style={{display: "flex", justifyContent: "center", margin: 10}}><Pagination count={10} page={page} onChange={handleChangePage} /></div>
      <Typography fontSize={30}>Offers</Typography>
      {/* <OffersTable offers={offers}/> */}
    </div>
  )
}

export default OwnerDashboard