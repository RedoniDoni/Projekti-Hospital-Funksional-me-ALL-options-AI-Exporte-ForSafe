import React from "react";
import { Routes } from "react-router-dom";
import { NurseRoutes } from "../routes/routesConfig";
import NurseSidebar from '../sidebar/NurseSidebar';
import generateRoutes from "../utils/generateRoutes";
import "./Layout.scss";


const NurseLayout = () => (
  <div className="layout">
    <NurseSidebar />
    <div className="content">
      <Routes>
        {generateRoutes(NurseRoutes)}
      </Routes>
    </div>
  </div>
);

export default NurseLayout;
