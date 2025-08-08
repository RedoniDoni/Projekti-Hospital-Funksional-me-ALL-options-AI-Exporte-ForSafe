import React from 'react';
import doctorimage from '../../assets/doctorimage.jpg';
import './DoctorDashboard.scss';

const DoctorDashboard = () => {
  return (
    <div className="patient-dashboard">
      <main className="main-content" style={{ backgroundImage: `url(${doctorimage})` }}>
        <header className="header">
          <h1>Welcome Doctor</h1>
          <p>Your career at your fingertips.</p>
        </header>
        <section id="overview" className="section">
          <h2>Overview</h2>
          <div className="card-container">
            <div className="card">
              <h3>Upcoming Working Days</h3>
              <p>See your own working dates</p>
            </div>
            <div className="card">
              <h3>Messages</h3>
              <p>You have 2 new messages</p>
            </div>
            <div className="card">
              <h3>Updates</h3>
              <p>Checkup: You don't have any updates</p>
            </div>
          </div>
        </section>
        <section id="appointments" className="section">
          <h2>Appointments</h2>
          <div className="card-container">
            <div className="card">
              <h3>See Appointments</h3>
              <p>Here you can check for upcoming appointments.</p>
            </div>
          </div>
        </section>
        <section id="records" className="section">
          <h2>Medical Records</h2>
          <div className="card-container">
            <div className="card">
              <h3>Access Records</h3>
              <p>Access patients records securely.</p>
            </div>
          </div>
        </section>
        <section id="profile" className="section">
          <h2>Profile</h2>
          <div className="card-container">
            <div className="card">
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

export default DoctorDashboard;

