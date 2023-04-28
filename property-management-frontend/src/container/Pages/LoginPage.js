import { useContext, useState } from "react";
import { TextField, Button, Typography, Container, Grid } from "@mui/material";
import { Link, useLocation, useNavigate } from "react-router-dom";
import UserContext from "../../context/UserContext";
import axiosInstance from "../../api/axiosInstance";
import Cookies from 'universal-cookie';

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const { user, setUser } = useContext(UserContext);

  const navigate = useNavigate();
  const location = useLocation();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!/^\S+@\S+\.\S+$/.test(email)) {
      setEmailError("Invalid email address");
      return;
    }
    setEmailError("");

    if (password.length < 3) {
      setPasswordError("Password must be at least 3 characters");
      return;
    }
    setPasswordError("");

    axiosInstance.post('/authenticate/login', {email, password})
    .then(response => {
      console.log(response.data);

      if (!response.data.accessToken) {
        throw new Error('No token returned from backend');
      }

      const cookies = new Cookies();
      cookies.set('accessToken', response.data.accessToken, {
        path: '/',
      });

      if(response.data.roles[0] === 'ADMIN') {
        setUser({
          isAuthenticated: true,
          role: "admin",
        });
      } else if(response.data.roles[0] === 'OWNER') {
        setUser({
          isAuthenticated: true,
          role: "owner",
          id: response.data.userId
        });
      } else {
        setUser({
          isAuthenticated: true,
          role: "customer",
          id: response.data.userId
        });
      }

      console.log(user);
    })
    .catch(error => console.error(error))

    // if (email === "admin@gmail.com") {
    //   setUser({
    //     isAuthenticated: true,
    //     role: "admin",
    //   });
    // } else if(email === "owner@gmail.com") {
    //   setUser({
    //     isAuthenticated: true,
    //     role: "owner",
    //   });
    // } else if(email === "customer@gmail.com") {
    //   setUser({
    //     isAuthenticated: true,
    //     role: "customer",
    //   });
    // }

    if (location.state) navigate("/properties/" + location.state.propertyId);
    else navigate("/");
  };

  return (
    <Container maxWidth="xs">
      <Typography variant="h4" align="center">
        Login
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          id="email"
          label="Email Address"
          name="email"
          autoComplete="email"
          autoFocus
          error={emailError !== ""}
          helperText={emailError}
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          name="password"
          label="Password"
          type="password"
          id="password"
          autoComplete="current-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          error={passwordError !== ""}
          helperText={passwordError}
        />
        <Button type="submit" fullWidth variant="contained" color="primary">
          Sign In
        </Button>
        <Grid container gridRow={1}>
          <Grid item xs>
            <Link to={"/forgot-password"}>Forgot Password?</Link>
          </Grid>
          <Grid item xs>
            <Link to={"/register"}>Not Registered?</Link>
          </Grid>
        </Grid>
      </form>
    </Container>
  );
};

export default LoginPage;
