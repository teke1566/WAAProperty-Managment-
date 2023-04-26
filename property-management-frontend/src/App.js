import { useState } from "react";
import "./App.css";
import Header from "./container/Header/Header";
import UserContext from "./context/UserContext";
import PageRoutes from "./routes/PageRoutes";
import { BrowserRouter } from "react-router-dom";

function App() {
  const [user, setUser] = useState({
    isAuthenticated: false,
    username: null,
    role: "viewer",
  });

  return (
    <div className="App">
      <BrowserRouter>
        <UserContext.Provider value={{ user, setUser }}>
          <Header />
          <PageRoutes />
        </UserContext.Provider>
      </BrowserRouter>
    </div>
  );
}

export default App;
