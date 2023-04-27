import { useState } from "react";
import { TextField, Button, Typography, Container,Checkbox,FormControlLabel } from "@mui/material";
import { useNavigate } from "react-router";

const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");
  const [owner, setOwner] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!/^\S+@\S+\.\S+$/.test(email)) {
      setEmailError("Invalid email address");
      return;
    }
    setEmailError("");

    // Validate password
    if (password.length < 6) {
      setPasswordError("Password must be at least 6 characters");
      return;
    }
    setPasswordError("");

    // Validate confirm password
    if (confirmPassword !== password) {
      setConfirmPasswordError("Passwords do not match");
      return;
    }
    setConfirmPasswordError("");

    // Validate phone number
    if (!/^\d{10}$/.test(phoneNumber)) {
      setPhoneNumberError("Invalid phone number");
      return;
    }
    setPhoneNumberError("");
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
              label="First Name"
              name="firstName"
              autoComplete="given-name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="lastName"
              label="Last Name"
              name="lastName"
              autoComplete="family-name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="phoneNumber"
              label="Phone Number"
              name="phoneNumber"
              autoComplete="tel"
              inputProps={{
                pattern: "[0-9]{10}", // Only allow 10 digits
                maxLength: 10, // Limit the input to 10 characters
              }}
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
              error={phoneNumberError}
              helperText={phoneNumberError}
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
