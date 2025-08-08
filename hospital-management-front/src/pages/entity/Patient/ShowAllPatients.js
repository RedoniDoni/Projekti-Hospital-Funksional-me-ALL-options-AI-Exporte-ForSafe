import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { deletePatient, fetchAllPatients } from "../../../services/requests/patient";
import "./Patient.scss";
import { Modal } from "antd";

const ShowAllPatients = () => {
  const [patients, setPatients] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const data = await fetchAllPatients(); // Fetch the nurses array
        setPatients(data);
      } catch (error) {
        console.error("Error fetching patients:", error);
      }
    };
    fetchPatients();
  }, []);

  const handleDelete = async (id) => {
    Modal.confirm({
      title: "Are you sure you want to delete this patient?",
      content: "This action cannot be undone.",
      okText: "Yes",
      cancelText: "No",
      onOk: async () => {
        try {
          await deletePatient(id);
          setPatients(patients.filter((patient) => patient.id !== id));
        } catch (error) {
          console.error("Error deleting patient:", error);
        }
      },
    });
  };

  return (
    <div className="nurse-container">
      <h2>All patients</h2>
      <button className="add-button" onClick={() => navigate("/admin/patient/add")}>
        Add Patient
      </button>
      <table className="nurse-table">
        <thead>
          <tr>
          <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Date Of Birth</th>
            <th>Age</th>
            <th>street</th>
            <th>phoneNumber</th>
            <th>roomName</th>
            <th>cityName</th>
            <th>Actions</th>

          </tr>
        </thead>
        <tbody>
          {Array.isArray(patients) && patients.length > 0 ? (
            patients.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.id}</td>
                <td>{patient.firstName}</td>
                <td>{patient.lastName}</td>
                <td>{patient.email || "N/A"}</td>
                <td>{patient.dateOfBirth || "N/A"}</td>
                <td>{patient.age || "N/A"}</td>
                <td>{patient.street}</td>
                <td>{patient.phoneNumber || "N/A"}</td>
                <td>{patient.roomName || "N/A"}</td>
                <td>{patient.cityName || "N/A"}</td>
                <td>
                <button
                    className="details-button"
                    onClick={() => navigate(`/admin/patient/details/${patient.id}`)}
                  >
                    Details
                  </button>
                  <button
                    className="edit-button"
                    onClick={() => navigate(`/admin/patient/edit/${patient.id}`)} // Use patient.id
                  >
                    Edit
                  </button>
                  <button
                    className="delete-button"
                    onClick={() => handleDelete(patient.id)} // Use nurse.id
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="12">No patients available.</td>
            </tr>
          )}
        </tbody>

      </table>
    </div>
  );
};

export default ShowAllPatients;
