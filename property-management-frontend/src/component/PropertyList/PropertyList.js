import React from "react";
import { Grid, Card, CardMedia, CardContent, Typography } from "@mui/material";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import BorderColorSharpIcon from "@mui/icons-material/BorderColorSharp";
import CircleIcon from "@mui/icons-material/Circle";
import { Link } from "react-router-dom";
import styles from "../Dashboard/CustomerStyles";

const PropertyList = (props) => {
  return (
    <div>
      <Grid
        container
        spacing={1}
        style={{
          margin: 2,
          marginLeft: "auto",
          marginRight: "auto",
          paddingRight: 1,
          paddingBottom: 1,
        }}
        maxWidth="xl"
      >
        {props.properties.map((property) => (
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
                  <Typography style={{ fontWeight: "bold", fontSize: 20 }}>
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
    </div>
  );
};

export default PropertyList;
