import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAppointmentByDoctorId } from "../../../services/requests/reserveDoctor";
import "./appointments.scss";

const AllAppointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // Hook for navigation

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const token = localStorage.getItem("authToken");
        const decodedToken = JSON.parse(atob(token.split(".")[1]));
        console.log("decode", decodedToken);

        const data = await getAppointmentByDoctorId(decodedToken?.userId);
        setAppointments(data.data);
      } catch (err) {
        console.error("Error fetching appointments:", err);
        setError("Failed to fetch appointments. Please try again later.");
      } finally {
        setLoading(false);
      }
    };
    fetchAppointments();
  }, []);

  const handleCreateDiagnose = (appointmentId, patientId) => {
    navigate(`/doctor/appointments/create-diagnosis/${appointmentId}`, {
      state: { patientId },
    });
  };
  console.log("appointments", appointments);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div class="appointments-container">
      <h1 class="appointments-heading">All Appointments</h1>
      {appointments.length > 0 ? (
        <ul class="appointments-list">
          {appointments.map((appointment) => (
            <li key={appointment.appointmentId} class="appointment-item">
              <p>
                <strong>Patient Name:</strong> {appointment.patientName}
              </p>
              <p>
                <strong>Status:</strong> {appointment.status}
              </p>
              <p>
                <strong>Reason:</strong> {appointment.reason}
              </p>
              <button
                onClick={() =>
                  handleCreateDiagnose(
                    appointment.appointmentId,
                    appointment.patientId
                  )
                }
                class="create-diagnose-btn"
              >
                Create Diagnose
              </button>
            </li>
          ))}
        </ul>
      ) : (
        <p class="no-appointments-message">No appointments found.</p>
      )}
    </div>
  );
};

export default AllAppointments;
