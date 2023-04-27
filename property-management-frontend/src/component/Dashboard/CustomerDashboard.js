import React, { useEffect, useState } from "react";
import styles from "./CustomerStyles";
import Filters from "../Filters";
import Pagination from "@mui/material/Pagination";
import PropertyList from "../PropertyList/PropertyList";
import axiosInstance from "../../api/axiosInstance";

function CustomerDashboard(props) {
  const [page, setPage] = useState(1);
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    axiosInstance.get("properties")
    .then(response => setProperties(response.data))
    .catch(error => console.error(error));
  }, []);

  const handleChangePage = (event, value) => {
    setPage(value);
  };

  // const properties = [
  //   {
  //     id: 1,
  //     propertyName: "Spacious 2BR Apartment in Downtown",
  //     description:
  //       "This beautiful 2BR apartment is located in the heart of downtown, within walking distance of shops, restaurants, and public transportation. It features high ceilings, large windows, and plenty of natural light.",
  //     price: 1500,
  //     numberOfRooms: 2,
  //     numberOfBathRooms: 1,
  //     size: 1000,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1029599/pexels-photo-1029599.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "house",
  //     listType: "sale",
  //     status: "available",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 2,
  //     propertyName: "Charming 1BR Cottage near the Beach",
  //     description:
  //       "Escape to this cozy 1BR cottage just a few blocks from the beach. The cottage features a private patio, comfortable furnishings, and modern amenities. Perfect for a romantic getaway or a solo retreat.",
  //     price: 1200,
  //     numberOfRooms: 1,
  //     numberOfBathRooms: 1,
  //     size: 700,
  //     imageUrl:
  //       "https://images.pexels.com/photos/2102587/pexels-photo-2102587.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "apartment",
  //     listType: "rent",
  //     status: "pending",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 3,
  //     propertyName: "Luxurious 3BR Penthouse with Ocean Views",
  //     description:
  //       "Live the high life in this stunning 3BR penthouse with panoramic ocean views. The apartment features sleek modern design, top-of-the-line appliances, and a spacious terrace for entertaining.",
  //     price: 3000,
  //     numberOfRooms: 3,
  //     numberOfBathRooms: 2,
  //     size: 2000,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1115804/pexels-photo-1115804.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "condo",
  //     listType: "sale",
  //     status: "contingent",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 4,
  //     propertyName: "Modern 2BR Condo in Trendy Neighborhood",
  //     description:
  //       "Experience the best of city living in this stylish 2BR condo in one of the city's most vibrant neighborhoods. The condo features contemporary furnishings, an open floor plan, and a private balcony with city views.",
  //     price: 1800,
  //     numberOfRooms: 2,
  //     numberOfBathRooms: 2,
  //     size: 1200,
  //     imageUrl:
  //       "https://images.pexels.com/photos/2581922/pexels-photo-2581922.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "townhome",
  //     listType: "rent",
  //     status: "available",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 5,
  //     propertyName: "Cozy 1BR Apartment near Public Transportation",
  //     description:
  //       "This lovely 1BR apartment is conveniently located near public transportation, making it easy to get around the city. The apartment features a comfortable living space, well-equipped kitchen, and a peaceful bedroom.",
  //     price: 1000,
  //     numberOfRooms: 1,
  //     numberOfBathRooms: 1,
  //     size: 600,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1974596/pexels-photo-1974596.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "multi-family",
  //     listType: "sale",
  //     status: "pending",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 6,
  //     propertyName: "Spacious 2BR Apartment in Downtown",
  //     description:
  //       "This beautiful 2BR apartment is located in the heart of downtown, within walking distance of shops, restaurants, and public transportation. It features high ceilings, large windows, and plenty of natural light.",
  //     price: 1500,
  //     numberOfRooms: 2,
  //     numberOfBathRooms: 1,
  //     size: 1000,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1612351/pexels-photo-1612351.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "house",
  //     listType: "rent",
  //     status: "contingent",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 7,
  //     propertyName: "Charming 1BR Cottage near the Beach",
  //     description:
  //       "Escape to this cozy 1BR cottage just a few blocks from the beach. The cottage features a private patio, comfortable furnishings, and modern amenities. Perfect for a romantic getaway or a solo retreat.",
  //     price: 1200,
  //     numberOfRooms: 1,
  //     numberOfBathRooms: 1,
  //     size: 700,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1876045/pexels-photo-1876045.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "condo",
  //     listType: "sale",
  //     status: "pending",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 8,
  //     propertyName: "Luxurious 3BR Penthouse with Ocean Views",
  //     description:
  //       "Live the high life in this stunning 3BR penthouse with panoramic ocean views. The apartment features sleek modern design, top-of-the-line appliances, and a spacious terrace for entertaining.",
  //     price: 3000,
  //     numberOfRooms: 3,
  //     numberOfBathRooms: 2,
  //     size: 2000,
  //     imageUrl:
  //       "https://images.pexels.com/photos/1022936/pexels-photo-1022936.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "apartment",
  //     listType: "rent",
  //     status: "available",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 9,
  //     propertyName: "Modern 2BR Condo in Trendy Neighborhood",
  //     description:
  //       "Experience the best of city living in this stylish 2BR condo in one of the city's most vibrant neighborhoods. The condo features contemporary furnishings, an open floor plan, and a private balcony with city views.",
  //     price: 1800,
  //     numberOfRooms: 2,
  //     numberOfBathRooms: 2,
  //     size: 1200,
  //     imageUrl:
  //       "https://images.pexels.com/photos/2587054/pexels-photo-2587054.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "house",
  //     listType: "sale",
  //     status: "contingent",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  //   {
  //     id: 10,
  //     propertyName: "Cozy 1BR Apartment near Public Transportation",
  //     description:
  //       "This lovely 1BR apartment is conveniently located near public transportation, making it easy to get around the city. The apartment features a comfortable living space, well-equipped kitchen, and a peaceful bedroom.",
  //     price: 1000,
  //     numberOfRooms: 1,
  //     numberOfBathRooms: 1,
  //     size: 600,
  //     imageUrl:
  //       "https://images.pexels.com/photos/2510067/pexels-photo-2510067.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
  //     propertyType: "house",
  //     listType: "rent",
  //     status: "sold",
  //     address: {
  //       street: "123 Main St",
  //       city: "New York",
  //       statte: "NY",
  //       zipcode: "10001"
  //     }
  //   },
  // ];

  return (
    <div style={styles.root}>
      <Filters />
      <PropertyList properties={properties}/>
      <div style={styles.pagination}>
        <Pagination count={10} page={page} onChange={handleChangePage} />
      </div>
      <br/>
    </div>
  );
}

export default CustomerDashboard;
