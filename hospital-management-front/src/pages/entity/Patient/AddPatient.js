import React from "react";
import PatientForm from "./PatientForm";
import './Patient.scss';

const AddPatientPage = () => {
  return (
    <div className="add-patient-page">
      <h2>Add Patient</h2>
      <PatientForm />
    </div>
  );
};

export default AddPatientPage;