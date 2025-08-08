import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchPatientById } from "../../../services/requests/patient";

const formatDate = (dateArray) => {
    // Assuming dateArray format: [year, month, day, ...]
    const date = new Date(Date.UTC(dateArray[0], dateArray[1] - 1, dateArray[2]));
    return date.toLocaleDateString('en-CA');  // "en-CA" format will return yyyy-mm-dd
  };
const PatientDetails = () => {
    const { id } = useParams();
    const [patient, setPatient] = useState(null);
  
    useEffect(() => {
      const getPatientDetails = async () => {
        try {
          const response = await fetchPatientById(id);
          const data = response.data;
          
          // Format the dates here if needed
          if (data.createdAt) {
            data.createdAt = formatDate(data.createdAt);
          }
          if (data.appointments) {
            data.appointments.forEach((appointment) => {
              if (appointment.date) {
                appointment.date = formatDate(appointment.date);
              }
            });
          }
  
          setPatient(data);
        } catch (error) {
          console.error("Error fetching patient details:", error);
        }
      };
      getPatientDetails();
    }, [id]);
  
    if (!patient) {
      return <p>Loading patient details...</p>;
    }
  
  return (
    <div className="patient-details-container">
    <h2 className="section-title">Patient Details</h2>

    <div className="patient-info">
      <div className="patient-info-item">
        <strong>ID:</strong> {patient.id}
      </div>
      <div className="patient-info-item">
        <strong>Name:</strong> {patient.firstName} {patient.lastName}
      </div>
      <div className="patient-info-item">
        <strong>Email:</strong> {patient.email || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Phone Number:</strong> {patient.phoneNumber || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Street:</strong> {patient.street || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>City:</strong> {patient.cityName || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Room Name:</strong> {patient.roomName || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Date of Birth:</strong> {patient.dateOfBirth || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Age:</strong> {patient.age || "N/A"}
      </div>
      <div className="patient-info-item">
        <strong>Created At:</strong> {patient.createdAt}
      </div>
    </div>

    <h3 className="section-title">Appointments</h3>
    {patient.appointments && patient.appointments.length > 0 ? (
      <ul className="appointments-list">
        {patient.appointments.map((appointment) => (
          <li key={appointment.id} className="appointment-item">
            <div><strong>Date:</strong> {appointment.date}</div>
            <div><strong>Reason:</strong> {appointment.reason}</div>
            <div><strong>Status:</strong> {appointment.status}</div>
            <div><strong>Doctor:</strong> {appointment.doctorName}</div>
          </li>
        ))}
      </ul>
    ) : (
      <p>No appointments available.</p>
    )}

    <h3 className="section-title">Diagnoses</h3>
    {patient.diagnoses && patient.diagnoses.length > 0 ? (
      <ul className="diagnoses-list">
        {patient.diagnoses.map((diagnosis) => (
          <li key={diagnosis.id} className="diagnosis-item">
            <div><strong>Diagnosis Details:</strong> {diagnosis.diagnosisDetails}</div>
            <div><strong>Treatment Plan:</strong> {diagnosis.treatmentPlan}</div>
            <div><strong>Doctor:</strong> {diagnosis.doctorName}</div>
          </li>
        ))}
      </ul>
    ) : (
      <p>No diagnoses available.</p>
    )}
  </div>
  );
};

export default PatientDetails;
