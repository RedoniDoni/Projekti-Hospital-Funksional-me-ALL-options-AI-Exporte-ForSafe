import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import PrivateRoute from "./components/PrivateRoute";
import AdminLayout from "./layouts/AdminLayout";
import DoctorLayout from "./layouts/DoctorLayout";
import NurseLayout from "./layouts/NurseLayout";
import PatientLayout from "./layouts/PatientLayout";
import LoginAndRegisterPage from "./pages/sharedPages/LoginAndRegisterPage";
import Unauthorized from "./pages/sharedPages/Unauthorized";
import HomePage from "./pages/Home/homePage";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginAndRegisterPage />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/unauthorized" element={<Unauthorized />} />

        <Route path="/admin/*" element={<PrivateRoute roles={['ADMIN']} component={AdminLayout} />} />
        <Route path="/doctor/*" element={<PrivateRoute roles={['DOCTOR']} component={DoctorLayout} />} />
        <Route path="/patient/*" element={<PrivateRoute roles={['PATIENT']} component={PatientLayout} />} />
        <Route path="/nurse/*" element={<PrivateRoute roles={['NURSE']} component={NurseLayout} />} />
      </Routes>
    </Router>
  );
};

export default App;
