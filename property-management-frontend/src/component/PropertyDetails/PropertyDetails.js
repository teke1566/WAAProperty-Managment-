import React, { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router";
import {
  Grid,
  Typography,
  Button,
  Card,
  CardMedia,
  CardContent,
} from "@mui/material";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import BorderColorSharpIcon from "@mui/icons-material/BorderColorSharp";
import CircleIcon from "@mui/icons-material/Circle";
import SendIcon from '@mui/icons-material/Send';
import AddIcon from '@mui/icons-material/Add';
import UserContext from "../../context/UserContext";
import OfferDialog from "./OfferDialog";
import FavouriteDialog from "./FavouriteDialog";
import AttachMoneyIcon from '@mui/icons-material/AttachMoney';
import InquiryDialog from "./InquiryDialog";

const PropertyDetails = () => {
  const [openOfferDiaglog, setOpenOffer] = useState(false);
  const [openFavorites, setOpenFav] = useState(false);
  const [openInquiry, setOpenInquiry] = useState(false);

  const {user} = useContext(UserContext);

  const params = useParams();
  const navigate = useNavigate();

  const property = {
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
  };

  const checkUser = () => {
    if(!user.isAuthenticated) {
      navigate("/login",{state:{propertyId: params.id}})
    }
  }

  const handleOffer = () => {
    // checkUser();
    setOpenOffer(!openOfferDiaglog);
  }
  
  const handleFavouriteList = () => {
    // checkUser();
    setOpenFav(!openFavorites);
  }

  const handleInquiry = () => {
    // checkUser();
    setOpenInquiry(!openInquiry);
  }

  return (
      <div>
        <Card className="propertyDetails">

          <CardMedia
            component="img"
            image={property.image}
            alt={property.title}
            className="propertyImage"
            />
          <CardContent className="propertyContent">
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
            <Typography style={{ fontWeight: "bold", fontSize: 25 }}>
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
            <Button onClick={handleOffer} variant="contained" endIcon={<AttachMoneyIcon />} style={{margin: 4}} color="success">Make Offer</Button>
            <OfferDialog open={openOfferDiaglog} toggle={handleOffer}/>
            <Button onClick={handleFavouriteList} variant="contained" endIcon={<AddIcon />} style={{marginRight: 4}} color="error">Favorite</Button>
            <FavouriteDialog open={openFavorites} toggle={handleFavouriteList}/>
            <Button onClick={handleInquiry} variant="contained" endIcon={<SendIcon />} >Inquiry</Button>
            <InquiryDialog open={openInquiry} toggle={handleInquiry}/>
            <Button></Button>
            <Typography variant="h6" gutterBottom fontWeight={"bold"}>
              Description:
            </Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
            <Typography variant="body1">{property.description}</Typography>
          </CardContent>
        </Card>
      </div>
  );
};

export default PropertyDetails;
