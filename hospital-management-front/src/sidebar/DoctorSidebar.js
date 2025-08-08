import {
  faBed,
  faCalendarCheck,
  faCalendarPlus,
  faEnvelope,
  faHospital,
  faSignOutAlt,
  faTachometerAlt,
  faUserCircle
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, useNavigate } from "react-router-dom";
import "./Sidebar.scss";

const DoctorSidebar = () => {
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
          <Link to="/doctor/dashboard">
            <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
          </Link>
        </li>
        <li>
          <Link to="/doctor/email">
            <FontAwesomeIcon icon={faEnvelope} /> Send Email
          </Link>
        </li>
        {/* <li>
          <Link to="/doctor/inventory-request">
            <FontAwesomeIcon icon={faBoxes} /> Manage Inventory
          </Link>
        </li> */}
        <li>
          <Link to="/doctor/allRoom">
            <FontAwesomeIcon icon={faHospital} /> Room
          </Link>
        </li>
        <li>
          <Link to="/doctor/room/patients">
            <FontAwesomeIcon icon={faBed} /> See Patients in Room
          </Link>
        </li>
        <li>
          <Link to="/doctor/appointments">
            <FontAwesomeIcon icon={faCalendarCheck} /> My Appointments
          </Link>
        </li>
        {/* Uncomment this line if you want to show the Patient Diagnosis */}
        {/* <li>
          <Link to="/doctor/diagnosis">
            <FontAwesomeIcon icon={faNotesMedical} /> Show Patient Diagnosis
          </Link>
        </li> */}
        <li>
          <Link to="/doctor/vacation/create">
            <FontAwesomeIcon icon={faCalendarPlus} /> Request Vacation
          </Link>
        </li>
        <li>
          <Link to="/doctor/Myprofile">
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

export default DoctorSidebar;
