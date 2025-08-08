import React from "react";
import RoomForm from "./RoomForm";
import "./createRoom.scss"

const AddRoomPage = () => {
  return (
    <div className="add-room-page">
      <h2>Add Room</h2>
      <RoomForm />
    </div>
  );
};

export default AddRoomPage;
