import React from "react";
import { useParams } from "react-router-dom";
import PatientForm from "./PatientForm";

const EditPatientPage = () => {
  const { id } = useParams(); // Get the ID from the route parameters

  return (
    <div className="edit-patient-page">
      <h2>Edit Patient</h2>
      <PatientForm id={id} /> {/* Pass the ID to PatientForm */}
    </div>
  );
};

export default EditPatientPage;
