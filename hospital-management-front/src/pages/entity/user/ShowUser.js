import React, { useEffect, useState } from "react";
import { getUserData } from "../../../services/requests/auth";
import "./user.scss";

const ShowUser = () => {
  const [user, setUser] = useState(null); // State for user data
  const [error, setError] = useState(null); // State for handling errors

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userData = await getUserData();
        console.log("Fetched user data:", userData); // Debug: Log fetched data
        setUser(userData); // Set the user data in state
      } catch (err) {
        console.error("Error fetching user data:", err);
        setError("Failed to load user data."); // Handle errors
      }
    };

    fetchUser();
  }, []);

  return (
    <div className="show-user">
      <h2>User Details</h2>
      {error ? (
        <p className="error">{error}</p>
      ) : user ? (
        <table className="user-table">
          <tbody>
            <tr>
              <th>First Name</th>
              <td>{user.firstName}</td>
            </tr>
            <tr>
              <th>Last Name</th>
              <td>{user.lastName}</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>{user.email}</td>
            </tr>
            <tr>
              <th>Phone Number</th>
              <td>{user.phoneNumber}</td>
            </tr>
            <tr>
              <th>Role</th>
              <td>{user.role}</td>
            </tr>
            <tr>
              <th>Status</th>
              <td>{user.status}</td>
            </tr>
          </tbody>
        </table>
      ) : (
        <p>Loading user data...</p>
      )}
    </div>
  );
};

export default ShowUser;
