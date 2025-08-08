import React, { useEffect, useState } from "react";
import { fetchPatientsInRoom } from "../../../services/requests/patient";
import "./RoomPatients.scss";

const RoomPatients = () => {
  const [rooms, setRooms] = useState([]); // Inicializo gjendjen
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const data = await fetchPatientsInRoom();
        setRooms(data || []); 
      } catch (err) {
        console.error("Error fetching patients in rooms:", err);
        setError("Failed to fetch room data. Please try again later.");
      } finally {
        setLoading(false);
      }
    };
  
    fetchRooms();
  }, []);
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="room-patients-container">
      <h2>Patients by Room</h2>
      {rooms.length === 0 ? (
  <p>No data available.</p>
) : (
  rooms.map((room) => (
    <div key={room.roomName} className="room-card">
      <h3>{room.roomName}</h3>
      {room.patients && room.patients.length > 0 ? (
        <table className="patient-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Age</th>
            </tr>
          </thead>
          <tbody>
            {room.patients.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.id}</td>
                <td>{patient.firstName}</td>
                <td>{patient.lastName}</td>
                <td>{patient.email || "N/A"}</td>
                <td>{patient.age || "N/A"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No patients available in this room.</p>
      )}
    </div>
  ))
)}

    </div>
  );
};

export default RoomPatients;
