import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validate email address
    if (!email) {
      setError("Email address is required.");
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      setError("Please enter a valid email address.");
    } else {
      // Send password reset email to user's email address
      // If successful, set success message
      setSuccess(true);
    }
  };

  return (
    <div>
      <h1>Forgot Your Password?</h1>
      <form onSubmit={handleSubmit}>
        {success ? (
          <div style={{ color: "green", marginBottom: 10 }}>
            Password reset email sent to {email}.
          </div>
        ) : (
          <>
            <TextField
              label="Email"
              variant="outlined"
              size="small"
              value={email}
              error={error}
              helperText={error}
              onChange={(e) => setEmail(e.target.value)}
              style={{width: 400}}
            />
            <Button
              variant="contained"
              color="primary"
              size="large"
              type="submit"
              style={{marginLeft: 10}}
            >
              Submit
            </Button>
            <br/>
            <Button variant="outlined" onClick={() => navigate('/login')} style={{marginTop: 20}}>
              Back
            </Button>
          </>
        )}
      </form>
    </div>
  );
};

export default ForgotPassword;
