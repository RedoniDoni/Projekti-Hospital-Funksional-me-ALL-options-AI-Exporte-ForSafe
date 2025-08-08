import React from "react";
import NurseForm from "./NurseForm";
import "./Nurse.scss";

const AddNursePage = () => {
  return (
    <div className="add-nurse-page">
      <h2>Add Nurse</h2>
      <NurseForm />
    </div>
  );
};

export default AddNursePage;
