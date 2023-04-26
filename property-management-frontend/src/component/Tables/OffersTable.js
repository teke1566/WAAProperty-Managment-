import React from "react";
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
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';

const OffersTable = ({ offers }) => {
  return (
    <TableContainer component={Paper}>
      <Table aria-label="offers table">
        <TableHead>
          <TableRow>
            <TableCell>Property Image</TableCell>
            <TableCell align="center">Title</TableCell>
            <TableCell align="center">Offer Value</TableCell>
            <TableCell align="center">Customer</TableCell>
            <TableCell align="center">Status</TableCell>
            <TableCell align="center">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {offers.map((offer) => (
            <TableRow key={offer.id}>
              <TableCell>
                <img src={offer.property.image} alt="property" height="50" />
              </TableCell>
              <TableCell align="center">{offer.property.title}</TableCell>
              <TableCell align="center">{offer.value}</TableCell>
              <TableCell align="center">{offer.customer}</TableCell>
              <TableCell align="center">
                {offer.status === "pending" ? <Typography color={"#f8b21d"}>{offer.status}</Typography> : null}
                {offer.status === "accepted" ? <Typography color={'green'}>{offer.status}</Typography> : null}
                {offer.status === "rejected" ? <Typography color={'red'}>{offer.status}</Typography> : null}
                
                </TableCell>
              <TableCell align="center">
                <IconButton disabled={offer.status !== "pending"}>
                  <CheckIcon style={{ color: "green" }} />
                </IconButton>
                <IconButton disabled={offer.status !== "pending"}>
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
