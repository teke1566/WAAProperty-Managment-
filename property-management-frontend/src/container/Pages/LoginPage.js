import { useContext, useState } from "react";
import { TextField, Button, Typography, Container, Grid } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import UserContext from "../../context/UserContext";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const {user, setUser} = useContext(UserContext);

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!/^\S+@\S+\.\S+$/.test(email)) {
      setEmailError("Invalid email address");
      return;
    }
    setEmailError("");

    if (password.length < 6) {
      setPasswordError("Password must be at least 6 characters");
      return;
    }
    setPasswordError("");

    setUser({
        isAuthenticated: true,
        username: "username",
        role: "admin"
    })

    navigate('/');
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
          error={emailError}
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
          error={passwordError}
          helperText={passwordError}
        />
        <Button type="submit" fullWidth variant="contained" color="primary">
          Sign In
        </Button>
        <Grid container gridRow={1}>
          <Grid item xs >
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
