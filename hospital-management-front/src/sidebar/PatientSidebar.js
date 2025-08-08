import {
  faCalendarCheck,
  faEnvelope,
  faFileMedical,
  faSignOutAlt,
  faTachometerAlt,
  faUserCircle,
  faUserMd,
  faUserNurse
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, useNavigate } from "react-router-dom";
import "./Sidebar.scss";

const PatientSidebar = () => {
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
          <Link to="/patient/dashboard">
            <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
          </Link>
        </li>
        <li>
          <Link to="/patient/email">
            <FontAwesomeIcon icon={faEnvelope} /> Send Email
          </Link>
        </li>
        <li>
          <Link to="/patient/allDoctor">
            <FontAwesomeIcon icon={faUserMd} /> See Doctors
          </Link>
        </li>
        <li>
          <Link to="/patient/allNurse">
            <FontAwesomeIcon icon={faUserNurse} /> See Nurses
          </Link>
        </li>
        {/* <li>
          <Link to="/patient/feedback/nurse">
            <FontAwesomeIcon icon={faComments} /> See Nurses Feedback
          </Link>
        </li> */}
        {/* <li>
          <Link to="/patient/feedback/create">
            <FontAwesomeIcon icon={faEdit} /> Give Feedback for Nurse
          </Link>
        </li>
        <li>
          <Link to="/patient/feedback/createDoctor">
            <FontAwesomeIcon icon={faEdit} /> Give Feedback for Doctor
          </Link>
        </li> */}
        <li>
          <Link to="/patient/reserveDoctor">
            <FontAwesomeIcon icon={faCalendarCheck} /> Reserve Doctor
          </Link>
        </li>
        <li>
          <Link to="/patient/show-diagnosis">
            <FontAwesomeIcon icon={faFileMedical} /> My history
          </Link>
        </li>
        <li>
          <Link to="/patient/Myprofile">
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

export default PatientSidebar;
