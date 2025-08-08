import { Modal } from "antd";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as XLSX from "xlsx";
import { deleteRoom, fetchAllRooms } from "../../../services/requests/rooms";
import "./createRoom.scss"; // Reuse styles or create a new one for rooms


const ShowAllRooms = () => {
  const [rooms, setRooms] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const { data } = await fetchAllRooms(); // Fetch the rooms array
        setRooms(data);
      } catch (error) {
        console.error("Error fetching rooms:", error);
      }
    };
    fetchRooms();
  }, []);

  const handleDelete = async (id) => {
    Modal.confirm({
      title: "Are you sure you want to delete this room?",
      content: "This action cannot be undone.",
      okText: "Yes",
      cancelText: "No",
      onOk: async () => {
        try {
          await deleteRoom(id);
          setRooms(rooms.filter((room) => room.id !== id));
        } catch (error) {
          console.error("Error deleting room:", error);
        }
      },
    });
  };

  const handleExportToExcel = () => {
  const worksheet = XLSX.utils.json_to_sheet(rooms);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Rooms");
  XLSX.writeFile(workbook, "RoomData.xlsx");
};


  return (
    <div className="room-container">
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '15px' }}>
  <h2>All Rooms</h2>
  <div>
    <button
      className="add-button"
      onClick={() => navigate("/admin/room/add")}
      style={{ marginRight: "10px" }}
    >
      Add Room
    </button>
    <button
      style={{
        backgroundColor: '#4CAF50',
        color: 'white',
        padding: '8px 12px',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        transition: '0.3s'
      }}
      onClick={handleExportToExcel}
    >
      Export to Excel
    </button>
  </div>
</div>

      <table className="doctor-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Room Name</th>
            <th>Description</th>
            <th>Floor</th>
            <th>Number of Beds</th>
            <th>Department</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(rooms) && rooms.length > 0 ? (
            rooms.map((room) => (
              <tr key={room.id}>
                <td>{room.id}</td>
                <td>{room.roomName}</td>
                <td>{room.description || "N/A"}</td>
                <td>{room.floor}</td>
                <td>{room.nrOfBeds}</td>
                <td>{room.departament ? room.departament : "N/A"}</td>
                <td>
                  <button
                    className="edit-button"
                    onClick={() => navigate(`/admin/room/edit/${room.id}`)}
                  >
                    Edit
                  </button>
                  <button
                    className="delete-button"
                    onClick={() => handleDelete(room.id)}
                  >
                    Delete
                  </button>
                  <button
                    className="view-patients-button details-button"
                    onClick={() => navigate(`/admin/room/${room.id}/patients`)}
                  >
                    Show All Patients in Room
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No rooms available.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ShowAllRooms;

