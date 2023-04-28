import React, { useContext, useEffect, useState } from "react";
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Paper,
  IconButton,
  Typography,
} from "@mui/material";
import CheckIcon from "@mui/icons-material/Check";
import ClearIcon from "@mui/icons-material/Clear";
import axiosInstance from "../../api/axiosInstance";
import UserContext from "../../context/UserContext";
import { toast } from "react-toastify";

const OffersTable = () => {
  const [offers, setOffers] = useState([]);
  const [flag, setFlag] = useState(true);

  // const [properties, setProperties] = useState([]);

  const { user } = useContext(UserContext);

  console.log("user.role= ", user.role);

  useEffect(() => {
    console.log("user.role= ", user.role);
    if (user.role === "customer") {
      axiosInstance
        .get(`/customer/offer-history/${user.id}`)
        .then((response) => {
          const newOffers = response.data;
          console.log(newOffers);
          axiosInstance
            .get(`/customer/offer-history/${user.id}/properties`)
            .then((response2) => {
              const properties = response2.data;
              newOffers.map(
                (offer, index) => (offer.property = properties[index])
              );
              console.log(newOffers);
              setOffers(newOffers);
            })
            .catch((error) => console.error(error));
        })
        .catch((error) => console.error(error));
    } else if (user.role === "owner") {
      axiosInstance
        .get(`/properties/owner/${user.id}/active-offers`)
        .then((response) => {
          const newOffers = response.data;
          console.log(newOffers);
          axiosInstance
            .get(`/properties/owner/${user.id}/active-offers/properties`)
            .then((response2) => {
              const properties = response2.data;
              newOffers.map(
                (offer, index) => (offer.property = properties[index])
              );
              console.log(newOffers);
              setOffers(newOffers);
            })
            .catch((error) => console.error(error));
        })
        .catch((error) => console.error(error));
    }
  }, [flag]);

  const handleAccept = (id) => {
    // axiosInstance.
    
    toast.success("Accepted offer successfully!");
    if (user.role === "owner") {
      axiosInstance
        .post(`/properties/owner/offer/${id}/accept`)
        .then((response) => console.log(response.data))
        .catch((error) => console.error(error));
      const newOffers = offers.filter((offer) => offer.id !== id);
      console.log("newOffers ", newOffers);
      setOffers(newOffers);
    } else {
      axiosInstance
        .post(`/customer/offer/${id}/accept`)
        .then((response) => console.log(response.data))
        .catch((error) => console.error(error));
      const newOffers = offers.filter((offer) => offer.id !== id);
      console.log("newOffers ", newOffers);
      setOffers(newOffers);
    }
  };

  const handleReject = (id) => {
    // axiosInstance.
    toast.success("Rejected offer successfully!");
    axiosInstance
      .post(`/properties/owner/offer/${id}/reject`)
      .then((response) => console.log(response.data))
      .catch((error) => console.error(error));
    const newOffers = offers.filter((offer) => offer.id !== id);

    setOffers(newOffers);
    console.log("newOffers ", newOffers);
    console.log(offers);
  };
  return (
    <TableContainer component={Paper}>
      <Table aria-label="offers table">
        <TableHead>
          <TableRow>
            <TableCell>Property Image</TableCell>
            <TableCell align="center">Title</TableCell>
            <TableCell align="center">Offer Amount</TableCell>
            {/* <TableCell align="center">Customer</TableCell> */}
            <TableCell align="center">Status</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {offers.map((offer) => (
            <TableRow key={offer.id}>
              <TableCell>
                <img
                  src={offer.property?.imageUrl}
                  alt="property"
                  height="50"
                />
              </TableCell>
              <TableCell align="center">
                {offer.property?.propertyName}
              </TableCell>
              <TableCell align="center">{offer.amount}</TableCell>
              {/* <TableCell align="center">{offer.customer}</TableCell> */}
              <TableCell align="center">
                {offer.status === "PENDING" ? (
                  <Typography color={"#f8b21d"}>{offer.status}</Typography>
                ) : null}
                {offer.status === "ACCEPTED" ? (
                  <Typography color={"green"}>{offer.status}</Typography>
                ) : null}
                {offer.status === "REJECTED" ? (
                  <Typography color={"red"}>{offer.status}</Typography>
                ) : null}
              </TableCell>
              <TableCell align="center">
                <IconButton
                  disabled={user.role === 'customer' ? offer.status === "PENDING" : offer.status !== "PENDING"}
                  onClick={() => handleAccept(offer.id)}
                >
                  <CheckIcon style={{ color: "green" }} />
                </IconButton>
                <IconButton
                  disabled={offer.status !== "PENDING"}
                  onClick={() => handleReject(offer.id)}
                >
                  <ClearIcon style={{ color: "red" }} />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default OffersTable;
