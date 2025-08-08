import React from 'react';
import { Routes } from 'react-router-dom';
import { PatientRoutes } from '../routes/routesConfig';
import PatientSidebar from '../sidebar/PatientSidebar';
import generateRoutes from '../utils/generateRoutes';
import "./Layout.scss";


const PatientLayout = () => (
  <div className="layout">
    <PatientSidebar />
    <div className="content">
      <Routes>
        {generateRoutes(PatientRoutes)}
      </Routes>
    </div>
  </div>
);

export default PatientLayout;
