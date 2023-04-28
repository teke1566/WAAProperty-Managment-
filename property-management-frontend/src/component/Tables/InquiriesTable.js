import React, { useEffect, useState } from "react";
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

const InquiriesTable = () => {
  const [inquiries, setInquiries] = useState([]);

  useEffect(() => {
    axiosInstance
      .get("/customer/1/active-offers")
      .then((response) => {
        setInquiries(response.data);
        console.log(response.data);
      })
      .catch((error) => console.error(error));
    console.log(setInquiries);
  }, []);

  return (
    <TableContainer component={Paper}>
      <Table aria-label="offers table">
        <TableHead>
          <TableRow>
            <TableCell>Property Image</TableCell>
            <TableCell align="center">Customer</TableCell>
            <TableCell align="center">Inquiry</TableCell>
            <TableCell align="center">Status</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {inquiries.map((inquiry) => (
            <TableRow key={inquiry.id}>
              <TableCell>
                {/* <img src={inquiry.property.imageUrl} alt="property" height="50" /> */}
              </TableCell>
              {/* <TableCell align="center">{inquiry.property.title}</TableCell> */}
              <TableCell align="center">{inquiry.amount}</TableCell>
              <TableCell align="center">{inquiry.customer}</TableCell>
              <TableCell align="center">
                {inquiry.status === "pending" ? (
                  <Typography color={"#f8b21d"}>{inquiry.status}</Typography>
                ) : null}
                {inquiry.status === "accepted" ? (
                  <Typography color={"green"}>{inquiry.status}</Typography>
                ) : null}
                {inquiry.status === "rejected" ? (
                  <Typography color={"red"}>{inquiry.status}</Typography>
                ) : null}
              </TableCell>
              <TableCell align="center">
                <IconButton disabled={inquiry.status !== "pending"}>
                  <CheckIcon style={{ color: "green" }} />
                </IconButton>
                <IconButton disabled={inquiry.status !== "pending"}>
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

export default InquiriesTable;
