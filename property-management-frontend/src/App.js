import { useState } from "react";
import "./App.css";
import Header from "./container/Header/Header";
import UserContext from "./context/UserContext";
import PageRoutes from "./routes/PageRoutes";
import { BrowserRouter } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  const [user, setUser] = useState({
    isAuthenticated: false,
    id: null,
  });

  return (
    <div className="App">
      <BrowserRouter>
        <UserContext.Provider value={{ user, setUser }}>
          <Header />
          <PageRoutes />
          <ToastContainer />
        </UserContext.Provider>
      </BrowserRouter>
    </div>
  );
}

export default App;
