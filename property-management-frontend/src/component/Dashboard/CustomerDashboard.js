import React, { useContext, useEffect, useState } from "react";
import styles from "./CustomerStyles";
import Filters from "../Filters";
import Pagination from "@mui/material/Pagination";
import PropertyList from "../PropertyList/PropertyList";
import axiosInstance from "../../api/axiosInstance";
import UserContext from "../../context/UserContext";

function CustomerDashboard(props) {
  const [page, setPage] = useState(1);
  const [properties, setProperties] = useState([]);
  // const [filters, setFilters] = useState(null)
  const [city, setcity] = useState("");
  const [numberOfRooms, setrooms] = useState("");
  const [propertyType, settype] = useState("");
  const [price, setprice] = useState("");

  // useEffect(() => {
  //   axiosInstance.get("properties")
  //   .then(response => setProperties(response.data))
  //   .catch(error => console.error(error));
  // }, []);

  const { user } = useContext(UserContext);

  console.log(user);

  useEffect(() => {
    const reqParams = {
      city,
      numberOfRooms,
      propertyType,
      price,
    };
    console.log(reqParams);
    axiosInstance
      .get("properties/criteria", {
        params: reqParams,
      })
      .then((response) => setProperties(response.data))
      .catch((error) => console.error(error));
  }, [city, numberOfRooms, propertyType, price]);

  const handleChangePage = (event, value) => {
    setPage(value);
  };

  const handleFilter = (city, numberOfRooms, propertyType, price) => {
    console.log({ city, numberOfRooms, propertyType, price });
    // setFilters({city, numberOfRooms, propertyType, price});
    if (city) setcity(city);
    else setcity(null);
    if (numberOfRooms) setrooms(numberOfRooms);
    else setrooms(null);
    if (propertyType) settype(propertyType);
    else settype(null);
    if (price) setprice(price);
    else setprice(null);
  };

  return (
    <div style={styles.root}>
      <Filters handleFilter={handleFilter} />
      <PropertyList properties={properties} />
      <div style={styles.pagination}>
        <Pagination count={10} page={page} onChange={handleChangePage} />
      </div>
      <br />
    </div>
  );
}

export default CustomerDashboard;
