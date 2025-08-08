import React from "react";
import { useParams } from "react-router-dom";
import NurseForm from "./NurseForm";

const EditNursePage = () => {
  const { id } = useParams(); // Get the ID from the route parameters

  return (
    <div className="edit-nurse-page">
      <h2>Edit Nurse</h2>
      <NurseForm id={id} /> {/* Pass the ID to NurseForm */}
    </div>
  );
};

export default EditNursePage;
