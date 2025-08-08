import React, { useEffect, useState } from "react";
import { fetchAllNurses } from "../../services/requests/nurse";
// import "./Nurse.scss";

const SeeNurse = () => {
  const [nurses, setNurses] = useState([]);
//   const navigate = useNavigate();

  useEffect(() => {
    const fetchNurses = async () => {
      try {
        const data = await fetchAllNurses(); // Fetch the nurses array
        setNurses(data);
      } catch (error) {
        console.error("Error fetching nurses:", error);
      }
    };
    fetchNurses();
  }, []);

//   const handleDelete = async (id) => {
//     if (window.confirm("Are you sure you want to delete this nurse?")) {
//       try {
//         await deleteNurse(id);
//         setNurses(nurses.filter((nurse) => nurse.id !== id));
//       } catch (error) {
//         console.error("Error deleting nurse:", error);
//       }
//     }
//   };

  return (
    <div className="nurse-container">
      <h2>All Nurses</h2>
      {/* <button className="add-button" onClick={() => navigate("/admin/nurse/add")}>
        Add Nurse
      </button> */}
      <table className="nurse-table">
        <thead>
          <tr>
          <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Description</th>
            <th>street</th>
            <th>category</th>
            <th>phoneNumber</th>
            <th>roomName</th>
            <th>departmentName</th>
            <th>cityName</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(nurses) && nurses.length > 0 ? (
            nurses.map((nurse) => (
              <tr key={nurse.id}>
                <td>{nurse.id}</td>
                <td>{nurse.firstName}</td>
                <td>{nurse.lastName}</td>
                <td>{nurse.email || "N/A"}</td>
                <td>{nurse.description || "N/A"}</td>
                <td>{nurse.street}</td>
                <td>{nurse.category || "N/A"}</td>
                <td>{nurse.phoneNumber || "N/A"}</td>
                <td>{nurse.roomName || "N/A"}</td>
                <td>{nurse.departmentName || "N/A"}</td>
                <td>{nurse.cityName || "N/A"}</td>
                <td>
                  {/* <button
                    className="edit-button"
                    onClick={() => navigate(`/admin/nurse/edit/${nurse.id}`)} // Use nurse.id
                  >
                    Edit
                  </button>
                  <button
                    className="delete-button"
                    onClick={() => handleDelete(nurse.id)} // Use nurse.id
                  >
                    Delete
                  </button> */}
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="12">No nurses available.</td>
            </tr>
          )}
        </tbody>

      </table>
    </div>
  );
};

export default SeeNurse;
