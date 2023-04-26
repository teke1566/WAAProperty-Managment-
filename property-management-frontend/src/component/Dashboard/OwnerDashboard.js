import React, { useState } from 'react'
import OffersTable from '../Tables/OffersTable';
import PropertyList from '../PropertyList/PropertyList';
import { Button, Typography } from '@mui/material';
import Filters from '../Filters'
import Pagination from "@mui/material/Pagination";
import AddIcon from '@mui/icons-material/Add';
import AddPropertyDialog from '../PropertyDetails/AddPropertyDialog';


const OwnerDashboard = () => {
  const [page, setPage] = useState(1);
  const [openAdd, setOpenAdd] = useState(false);

  const handleChangePage = (event, value) => {
    setPage(value);
  };
  const properties = [
    {
      id: 1,
      title: "Spacious 2BR Apartment in Downtown",
      description:
        "This beautiful 2BR apartment is located in the heart of downtown, within walking distance of shops, restaurants, and public transportation. It features high ceilings, large windows, and plenty of natural light.",
      price: 1500,
      bedrooms: 2,
      bathrooms: 1,
      size: 1000,
      image:
        "https://images.pexels.com/photos/1029599/pexels-photo-1029599.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "house",
      listType: "sale",
      status: "available",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 2,
      title: "Charming 1BR Cottage near the Beach",
      description:
        "Escape to this cozy 1BR cottage just a few blocks from the beach. The cottage features a private patio, comfortable furnishings, and modern amenities. Perfect for a romantic getaway or a solo retreat.",
      price: 1200,
      bedrooms: 1,
      bathrooms: 1,
      size: 700,
      image:
        "https://images.pexels.com/photos/2102587/pexels-photo-2102587.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "apartment",
      listType: "rent",
      status: "pending",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 3,
      title: "Luxurious 3BR Penthouse with Ocean Views",
      description:
        "Live the high life in this stunning 3BR penthouse with panoramic ocean views. The apartment features sleek modern design, top-of-the-line appliances, and a spacious terrace for entertaining.",
      price: 3000,
      bedrooms: 3,
      bathrooms: 2,
      size: 2000,
      image:
        "https://images.pexels.com/photos/1115804/pexels-photo-1115804.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "condo",
      listType: "sale",
      status: "contingent",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 4,
      title: "Modern 2BR Condo in Trendy Neighborhood",
      description:
        "Experience the best of city living in this stylish 2BR condo in one of the city's most vibrant neighborhoods. The condo features contemporary furnishings, an open floor plan, and a private balcony with city views.",
      price: 1800,
      bedrooms: 2,
      bathrooms: 2,
      size: 1200,
      image:
        "https://images.pexels.com/photos/2581922/pexels-photo-2581922.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "townhome",
      listType: "rent",
      status: "available",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 5,
      title: "Cozy 1BR Apartment near Public Transportation",
      description:
        "This lovely 1BR apartment is conveniently located near public transportation, making it easy to get around the city. The apartment features a comfortable living space, well-equipped kitchen, and a peaceful bedroom.",
      price: 1000,
      bedrooms: 1,
      bathrooms: 1,
      size: 600,
      image:
        "https://images.pexels.com/photos/1974596/pexels-photo-1974596.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "multi-family",
      listType: "sale",
      status: "pending",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 6,
      title: "Spacious 2BR Apartment in Downtown",
      description:
        "This beautiful 2BR apartment is located in the heart of downtown, within walking distance of shops, restaurants, and public transportation. It features high ceilings, large windows, and plenty of natural light.",
      price: 1500,
      bedrooms: 2,
      bathrooms: 1,
      size: 1000,
      image:
        "https://images.pexels.com/photos/1612351/pexels-photo-1612351.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "house",
      listType: "rent",
      status: "contingent",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 7,
      title: "Charming 1BR Cottage near the Beach",
      description:
        "Escape to this cozy 1BR cottage just a few blocks from the beach. The cottage features a private patio, comfortable furnishings, and modern amenities. Perfect for a romantic getaway or a solo retreat.",
      price: 1200,
      bedrooms: 1,
      bathrooms: 1,
      size: 700,
      image:
        "https://images.pexels.com/photos/1876045/pexels-photo-1876045.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "condo",
      listType: "sale",
      status: "pending",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 8,
      title: "Luxurious 3BR Penthouse with Ocean Views",
      description:
        "Live the high life in this stunning 3BR penthouse with panoramic ocean views. The apartment features sleek modern design, top-of-the-line appliances, and a spacious terrace for entertaining.",
      price: 3000,
      bedrooms: 3,
      bathrooms: 2,
      size: 2000,
      image:
        "https://images.pexels.com/photos/1022936/pexels-photo-1022936.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "apartment",
      listType: "rent",
      status: "available",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 9,
      title: "Modern 2BR Condo in Trendy Neighborhood",
      description:
        "Experience the best of city living in this stylish 2BR condo in one of the city's most vibrant neighborhoods. The condo features contemporary furnishings, an open floor plan, and a private balcony with city views.",
      price: 1800,
      bedrooms: 2,
      bathrooms: 2,
      size: 1200,
      image:
        "https://images.pexels.com/photos/2587054/pexels-photo-2587054.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "house",
      listType: "sale",
      status: "contingent",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
    {
      id: 10,
      title: "Cozy 1BR Apartment near Public Transportation",
      description:
        "This lovely 1BR apartment is conveniently located near public transportation, making it easy to get around the city. The apartment features a comfortable living space, well-equipped kitchen, and a peaceful bedroom.",
      price: 1000,
      bedrooms: 1,
      bathrooms: 1,
      size: 600,
      image:
        "https://images.pexels.com/photos/2510067/pexels-photo-2510067.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      type: "house",
      listType: "rent",
      status: "sold",
      address: "50 Riverside Blvd APT 4E, New York, NY 10069",
    },
  ];

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
      <OffersTable offers={offers}/>
    </div>
  )
}

export default OwnerDashboard