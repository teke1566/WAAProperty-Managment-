import React, { useState } from "react";
import {
  Grid,
  Card,
  CardMedia,
  CardContent,
  Typography,
  Button,
  IconButton,
} from "@mui/material";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import BorderColorSharpIcon from "@mui/icons-material/BorderColorSharp";
import CircleIcon from "@mui/icons-material/Circle";
import { ChevronLeft, ChevronRight } from "@mui/icons-material";
import { Link } from "react-router-dom";
import styles from "./CustomerStyles";
function CustomerDashboard(props) {
  const [page, setPage] = useState(0);
  const propertiesPerPage = 10; // Change this to adjust the number of properties per page

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
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
      address: "50 Riverside Blvd APT 4E, New York, NY 10069"
    },
  ];

  const displayedProperties = properties.slice(
    page * propertiesPerPage,
    (page + 1) * propertiesPerPage
  );

  return (
    <div style={styles.root}>
        <div className="filterWrap">
            
        </div>
      <Grid container spacing={2}>
        {displayedProperties.map((property) => (
          <Grid
            item
            key={property.id}
            xs={2.4}
            sm={2.4}
            md={2.4}
            lg={2.4}
            xl={2.4}
          >
            <Link to={"/properties/" + property.id}>
              <Card style={styles.card}>
                <CardMedia
                  component="img"
                  height="194"
                  image={property.image}
                  alt={property.title}
                />
                <CardContent style={styles.cardContent}>
                  <div className="statusWrap">
                    {property.status === "available" ? (
                      <CheckCircleIcon
                        color="success"
                        fontSize="small"
                        className="statusIcon"
                      />
                    ) : null}
                    {property.status === "pending" ? (
                      <AccessTimeIcon
                        style={{ color: "#f8b21d" }}
                        fontSize="small"
                        className="statusIcon"
                      />
                    ) : null}
                    {property.status === "contingent" ? (
                      <BorderColorSharpIcon
                        style={{ color: "#f8b21d" }}
                        fontSize="small"
                        className="statusIcon"
                      />
                    ) : null}
                    {property.status === "sold" ? (
                      <CircleIcon
                        color="action"
                        fontSize="small"
                        className="statusIcon"
                      />
                    ) : null}
                    <span className="statusText">{property.status}</span>
                  </div>
                  <Typography
                    style={{ fontWeight: "bold", fontSize: 20}}
                  >
                    {property.price}$
                  </Typography>
                  <Typography>
                    <strong>{property.bedrooms}</strong> bed{" "}
                    <strong>{property.bathrooms}</strong> bath{" "}
                    <strong>{property.size}</strong> sqft
                  </Typography>
                  <Typography>
                    <strong>{property.type}</strong> for{" "}
                    <strong>{property.listType}</strong>
                  </Typography>
                  <Typography fontSize={15}>{property.address}</Typography>
                </CardContent>
              </Card>
            </Link>
          </Grid>
        ))}
      </Grid>
      <div style={styles.pagination}>
        <IconButton
          disabled={page === 0}
          onClick={(event) => handleChangePage(event, page - 1)}
        >
          <ChevronLeft />
        </IconButton>
        <IconButton
          disabled={(page + 1) * propertiesPerPage >= properties.length}
          onClick={(event) => handleChangePage(event, page + 1)}
        >
          <ChevronRight />
        </IconButton>
      </div>
    </div>
  );
}

export default CustomerDashboard;
