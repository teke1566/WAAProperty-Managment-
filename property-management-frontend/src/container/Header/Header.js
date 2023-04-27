import React, { useContext } from "react";
import Button from "@mui/material/Button";
import "./Header.css";
import { Link, useNavigate } from "react-router-dom";
import UserContext from "../../context/UserContext";

const Header = () => {

  const { user, setUser } = useContext(UserContext);

  const navigate = useNavigate();

  const logout = () => {
    setUser({});
    navigate('/');
  }
  return (
    <div className="header">
      <label>Header</label>
      <Link to={"/"}>
        <Button variant="text">Home</Button>
      </Link>
      {user.role ? (
        <>
          <Button variant="text" onClick={() => logout()}>Logout</Button>
        </>
      ) : (
        <>
          <Link to={"/login"}>
            <Button variant="text">Login</Button>
          </Link>
          <Link to={"/register"}>
            <Button variant="text">Register</Button>
          </Link>
        </>
      )}
    </div>
  );
};

export default Header;
