import React, { useContext, useState,useEffect } from "react";
import { useNavigate, useParams } from "react-router";
import {
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
import SendIcon from "@mui/icons-material/Send";
import AddIcon from "@mui/icons-material/Add";
import UserContext from "../../context/UserContext";
import OfferDialog from "./OfferDialog";
import FavouriteDialog from "./FavouriteDialog";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import InquiryDialog from "./InquiryDialog";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddPropertyDialog from "./AddPropertyDialog";
import DeleteDialog from "./DeleteDialog";
import axiosInstance from "../../api/axiosInstance";

const PropertyDetails = () => {
  const [openOfferDiaglog, setOpenOffer] = useState(false);
  const [openFavorites, setOpenFav] = useState(false);
  const [openInquiry, setOpenInquiry] = useState(false);
  const [openEditDialog, setOpenEdit] = useState(false);
  const [openDeleteDialog, setOpenDelete] = useState(false);
  const [property, setProperty] = useState({});

  const params = useParams();

  useEffect(() => {
    axiosInstance.get("properties/" + params.id)
    .then(response => setProperty(response.data))
    .catch(error => console.error(error));
    console.log(property);
  }, [params.id]);

  const { user } = useContext(UserContext);

  const navigate = useNavigate();

  const checkUser = () => {
    if (!user.isAuthenticated) {
      navigate("/login", { state: { propertyId: params.id } });
    }
  };

  const handleOffer = () => {
    // checkUser();
    setOpenOffer(!openOfferDiaglog);
  };

  const handleFavouriteList = () => {
    // checkUser();
    setOpenFav(!openFavorites);
  };

  const handleInquiry = () => {
    // checkUser();
    setOpenInquiry(!openInquiry);
  };

  const handleEditDialog = () => {
    setOpenEdit(!openEditDialog);
  }

  const handleDeleteDialog = () => {
    setOpenDelete(!openDeleteDialog);
  }

  const handleDelete = () => {
    console.log("delete " + params.id);
  }

  return (
    <div>
      <Card className="propertyDetails">
        <CardMedia
          component="img"
          image={property.imageUrl}
          alt={property.propertyName}
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
            <strong>{property.numberOfRooms}</strong> bed{" "}
            <strong>{property.numberOfBathRooms}</strong> bath{" "}
            <strong>{property.size}</strong> sqft
          </Typography>
          <Typography>
            <strong>{property.propertyType}</strong> for{" "}
            <strong>{property.listType}</strong>
          </Typography>
          <Typography fontSize={15}>{property.address?.street}, {property.address?.city}, {property.address?.state} {property.address?.zipcode}</Typography>
          {user.role === "owner" ? (
            <>
              <Button
                onClick={handleEditDialog}
                variant="contained"
                endIcon={<EditIcon />}
                style={{ margin: 4 }}
                color="warning"
              >
                Edit
              </Button>
              <AddPropertyDialog open={openEditDialog} toggle={handleEditDialog} property={property}/>
              <Button
                onClick={handleDeleteDialog}
                variant="contained"
                endIcon={<DeleteIcon />}
                style={{ marginRight: 4 }}
                color="error"
              >
                Delete
              </Button>
              <DeleteDialog
                open={openDeleteDialog}
                toggle={handleDeleteDialog}
                handleDelete={handleDelete}
              />
              {/* <Button
                onClick={handleInquiry}
                variant="contained"
                color="success"
                endIcon={<CheckIcon />}
              >
                Mark as sold
              </Button>
              <InquiryDialog open={openInquiry} toggle={handleInquiry} /> */}
            </>
          ) : (
            <>
              <Button
                onClick={handleOffer}
                variant="contained"
                endIcon={<AttachMoneyIcon />}
                style={{ margin: 4 }}
                color="success"
              >
                Make Offer
              </Button>
              <OfferDialog open={openOfferDiaglog} toggle={handleOffer} />
              <Button
                onClick={handleFavouriteList}
                variant="contained"
                endIcon={<AddIcon />}
                style={{ marginRight: 4 }}
                color="error"
              >
                Favorite
              </Button>
              <FavouriteDialog
                open={openFavorites}
                toggle={handleFavouriteList}
              />
              <Button
                onClick={handleInquiry}
                variant="contained"
                endIcon={<SendIcon />}
              >
                Inquiry
              </Button>
              <InquiryDialog open={openInquiry} toggle={handleInquiry} />
            </>
          )}

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
