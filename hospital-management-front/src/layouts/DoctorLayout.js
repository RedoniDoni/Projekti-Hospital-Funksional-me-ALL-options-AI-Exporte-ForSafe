import React from 'react';
import { Routes } from 'react-router-dom';
import { DoctorRoutes } from '../routes/routesConfig';
import DoctorSidebar from '../sidebar/DoctorSidebar';
import generateRoutes from '../utils/generateRoutes';
import "./Layout.scss";




const DoctorLayout = () => (
  <div className="layout">
    <DoctorSidebar />
    <div className="content">
      <Routes>
        {generateRoutes(DoctorRoutes)}
      </Routes>
    </div>
  </div>
);

export default DoctorLayout;
