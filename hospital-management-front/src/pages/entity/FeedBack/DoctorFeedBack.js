import React, { useEffect, useState } from "react";
import { feedBackForDoctor } from "../../../services/requests/feedBack";

const DoctorFeedback = () => {
  const [feedback, setFeedback] = useState([]); // Default state is an empty array
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchDoctorFeedback = async () => {
      try {
        const data = await feedBackForDoctor();
        setFeedback(data || []); // Set feedback to an empty array if data is null
      } catch (error) {
        setError("Error fetching doctor feedback. Please try again later.");
        console.error("Error fetching doctor feedback:", error);
      }
    };
    fetchDoctorFeedback();
  }, []);

  return (
    <div className="feedback-container">
      <h2>Doctor Feedback</h2>
      {error && <p className="error-message">{error}</p>}
      {Array.isArray(feedback) && feedback.length > 0 ? (
        <table className="feedback-table">
          <thead>
            <tr>
              <th>Doctor ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Phone Number</th>
              <th>Comment</th>
              <th>Rating</th>
            </tr>
          </thead>
          <tbody>
            {feedback.map((item) => (
              <tr key={item.doctorId}>
                <td>{item.doctorId}</td>
                <td>{item.doctorFirstName || "N/A"}</td>
                <td>{item.doctorLastName || "N/A"}</td>
                <td>{item.doctorPhoneNumber || "N/A"}</td>
                <td>{item.comment || "No comment"}</td>
                <td>{item.rating || "Unrated"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p className="no-data-message">No feedback available for doctors.</p>
      )}
    </div>
  );
};

export default DoctorFeedback;
