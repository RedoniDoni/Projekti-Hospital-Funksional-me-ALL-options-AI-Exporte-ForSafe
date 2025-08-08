import {
  faBed,
  faCalendarPlus,
  faEnvelope,
  faHospital,
  faSignOutAlt,
  faTachometerAlt,
  faUserCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Sidebar.scss";

const NurseSidebar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Remove token and auth token from localStorage
    localStorage.removeItem("token");
    localStorage.removeItem("authToken");

    // Redirect to the login page (or home page)
    navigate("/login");
  };

  return (
    <div className="sidebar">
      <ul>
        <li>
          <Link to="/nurse/dashboard">
            <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
          </Link>
        </li>
        <li>
          <Link to="/nurse/email">
            <FontAwesomeIcon icon={faEnvelope} /> Send Email
          </Link>
        </li>
        <li>
          <Link to="/nurse/allRoom">
            <FontAwesomeIcon icon={faHospital} /> Room
          </Link>
        </li>
        <li>
          <Link to="/nurse/room/patients">
            <FontAwesomeIcon icon={faBed} /> See Patients in Room
          </Link>
        </li>
        <li>
          <Link to="/nurse/vacation/create">
            <FontAwesomeIcon icon={faCalendarPlus} /> Request Vacation
          </Link>
        </li>
        <li>
          <Link to="/nurse/Myprofile">
            <FontAwesomeIcon icon={faUserCircle} /> My Profile
          </Link>
        </li>
        <li>
          <Link to="/" onClick={handleLogout}>
            <FontAwesomeIcon icon={faSignOutAlt} /> Log Out
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default NurseSidebar;
