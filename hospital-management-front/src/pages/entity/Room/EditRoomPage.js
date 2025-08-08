import React from "react";
import { useParams } from "react-router-dom";
import RoomForm from "./RoomForm";

const EditRoomPage = () => {
  const { id } = useParams(); // Get the ID from the route parameters

  return (
    <div className="edit-room-page">
      <h2>Edit Room</h2>
      <RoomForm id={id} /> 
    </div>
  );
};

export default EditRoomPage;
