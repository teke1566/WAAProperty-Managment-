import { useState } from "react";
import { TextField, Button, Typography, Container,Checkbox,FormControlLabel } from "@mui/material";
import { useNavigate } from "react-router";
import axiosInstance from "../../api/axiosInstance";

const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
  const [owner, setOwner] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!/^\S+@\S+\.\S+$/.test(email)) {
      setEmailError("Invalid email address");
      return;
    }
    setEmailError("");

    // Validate password
    if (password.length < 3) {
      setPasswordError("Password must be at least 3 characters");
      return;
    }
    setPasswordError("");

    // Validate confirm password
    if (confirmPassword !== password) {
      setConfirmPasswordError("Passwords do not match");
      return;
    }
    setConfirmPasswordError("");

    axiosInstance.post('/authenticate/register',{email, password, name: firstName, isOwner: owner})
    .then(response => {
      console.log(response.data);
      navigate('/login');
    })
    .catch(error => console.error(error))
  };

  const navigate = useNavigate();

  return (
    <Container maxWidth="xs">
          <Typography variant="h4" align="center">
            Registration
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
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="confirmPassword"
              label="Confirm Password"
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              error={confirmPasswordError}
              helperText={confirmPasswordError}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="firstName"
              label="Full Name"
              name="firstName"
              autoComplete="given-name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
            <FormControlLabel control={<Checkbox checked={owner} onChange={() => setOwner(!owner)}/>} label="I am home owner" />
            
            <Button type="submit" fullWidth variant="contained" color="primary">
              Register
            </Button>
            {/* <Button variant="outlined" onClick={() => navigate('/login')} style={{marginTop: 10}}>
              Back
            </Button> */}
          </form>
    </Container>
  );
};

export default RegisterPage;
