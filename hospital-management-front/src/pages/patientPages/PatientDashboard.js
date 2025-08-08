import React from 'react';
import { useNavigate } from 'react-router-dom';
import hospitalRoom from '../../assets/hospitalroom.jpg';
import './PatientDashboard.scss';

const PatientDashboard = () => {
  const navigate = useNavigate();

  return (
    <div className="patient-dashboard">
      <main className="main-content" style={{ backgroundImage: `url(${hospitalRoom})` }}>
        <header className="header">
          <h1>Welcome Patient</h1>
          <p>Your health at your fingertips.</p>
        </header>
        <section id="overview" className="section">
          <h2>Overview</h2>
          <div className="card-container">
            <div className="card">
              <h3>Upcoming Appointments</h3>
              <p>3 Appointments this month</p>
            </div>
            <div className="card">
              <h3>Messages</h3>
              <p>You have 2 new messages</p>
            </div>
            <div className="card">
              <h3>Health Updates</h3>
              <p>Latest checkup: Excellent</p>
            </div>
          </div>
        </section>
        <section id="appointments" className="section">
          <h2>Appointments</h2>
          <div className="card-container">
            <div
              className="card"
              onClick={() => navigate('/patient/reserveDoctor')}
              style={{ cursor: 'pointer' }}
            >
              <h3>Manage Appointments</h3>
              <p>Here you can manage your appointments.</p>
            </div>
          </div>
        </section>
        <section id="records" className="section">
          <h2>Medical Records</h2>
          <div className="card-container">
            <div
              className="card"
              onClick={() => navigate('/patient/show-diagnosis')}
              style={{ cursor: 'pointer' }}
            >
              <h3>Access Records</h3>
              <p>Access your medical records securely.</p>
            </div>
          </div>
        </section>
        <section id="profile" className="section">
          <h2>Profile</h2>
          <div className="card-container">
            <div
              className="card"
              onClick={() => navigate('/patient/Myprofile')}
              style={{ cursor: 'pointer' }}
            >
              <h3>Profile Settings</h3>
              <p>Manage your personal information.</p>
            </div>
          </div>
        </section>
        <section id="support" className="section">
          <h2>Support</h2>
          <div className="card-container">
            <div className="card">
              <h3>Contact Support</h3>
              <p>Need help? Contact support for assistance.</p>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default PatientDashboard;
