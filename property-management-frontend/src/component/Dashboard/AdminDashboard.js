import {
  Card,
  CardContent,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
  TextField,
  MenuItem,
} from "@mui/material";
import { useEffect, useState } from "react";
import UserTable from "../Tables/UserTable";
import axiosInstance from "../../api/axiosInstance";

const AdminDashboard = () => {
  // Define state and data fetching logic here
  const [rentedProperties, setRentedProperties] = useState([]);
  const [component, setComponent] = useState("properties");

  useEffect(() => {
    axiosInstance
      .get("/properties")
      .then((response) => setRentedProperties(response.data))
      .catch((error) => console.error(error));
  }, []);

  const components = [
    { value: "properties", label: "Recent Properties" },
    { value: "users", label: "Recent Users" },
  ];

  return (
    <Card>
      <CardContent>
        <Typography variant="h5" component="h2">
          Admin Dashboard
        </Typography>
        <TextField
          style={{ marginTop: 10, paddingTop: 10 }}
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
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Property name</TableCell>
                    <TableCell>Property image</TableCell>
                    <TableCell align="right">Price</TableCell>
                    <TableCell align="right">Bought by</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rentedProperties.map((property) => (
                    <TableRow key={property.id}>
                      <TableCell component="th" scope="row">
                        {property.propertyName}
                      </TableCell>
                      <TableCell>
                        <img src={property.imageUrl} alt="property" height="50" />
                      </TableCell>
                      <TableCell align="right">{property.price}</TableCell>
                      <TableCell align="right">{property.rentedBy}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </>
        ) : null}
        {component === "users" ? (
          <>
            <UserTable />
          </>
        ) : null}
      </CardContent>
    </Card>
  );
};

export default AdminDashboard;
