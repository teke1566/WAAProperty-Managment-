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
  } from "@mui/material";
  import { useEffect, useState } from "react";
  import {faker} from "@faker-js/faker"
import UserTable from "../Tables/UserTable";
  
  const AdminDashboard = () => {
    // Define state and data fetching logic here
    const [rentedProperties, setRentedProperties] = useState([]);
  
    useEffect(() => {
      // Populate rented properties with fake data
      const rentedPropertiesData = [];
  
      for (let i = 0; i < 10; i++) {
        rentedPropertiesData.push({
          id: i+1,
          name: faker.address.streetName(),
          price: faker.datatype.number(),
          rentedBy: faker.name.findName(),
        });
      }
  
      setRentedProperties(rentedPropertiesData);
    }, []);
  
    return (
      <Card>
        <CardContent>
          <Typography variant="h5" component="h2">
            Admin Dashboard
          </Typography>
          <Typography variant="subtitle1" component="h3">
            Last 10 rented properties
          </Typography>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Property name</TableCell>
                  <TableCell align="right">Rental price</TableCell>
                  <TableCell align="right">Rented by</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rentedProperties.map((property) => (
                  <TableRow key={property.id}>
                    <TableCell component="th" scope="row">
                      {property.name}
                    </TableCell>
                    <TableCell align="right">{property.price}</TableCell>
                    <TableCell align="right">{property.rentedBy}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <Typography variant="subtitle1" component="h3">
            Most recent customers
          </Typography>
          <UserTable/>
        </CardContent>
      </Card>
    );
  };
  
  export default AdminDashboard;

  