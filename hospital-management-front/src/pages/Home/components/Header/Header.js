import React from "react";
import "./Header.css"
import HospitalLogo from "../../../../assets/hospital-icon.webp"
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate()
    return (
        <div className="header">
            <img className="header-img" src={HospitalLogo} alt="" />
            <div className="header-content">
                <p>Home</p>
                <p>About</p>
                <p>Contact</p>
                <h3 className="header-content-login" onClick={() => navigate("/login")}>Login</h3>
            </div>
        </div>
    )
}
export default Header;