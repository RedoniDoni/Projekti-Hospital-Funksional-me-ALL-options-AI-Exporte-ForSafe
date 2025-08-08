import React, { useEffect, useState } from "react";
import { feedBackForNurse } from "../../../services/requests/feedBack";

const NurseFeedback = () => {
  const [feedback, setFeedback] = useState([]); // Default state is an empty array
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchNurseFeedback = async () => {
      try {
        const data = await feedBackForNurse();
        setFeedback(data || []); // Set feedback to an empty array if data is null
      } catch (error) {
        setError("Error fetching nurse feedback. Please try again later.");
        console.error("Error fetching nurse feedback:", error);
      }
    };
    fetchNurseFeedback();
  }, []);

  return (
    <div className="feedback-container">
      <h2>Nurse Feedback</h2>
      {error && <p className="error-message">{error}</p>}
      {Array.isArray(feedback) && feedback.length > 0 ? (
        <table className="feedback-table">
          <thead>
            <tr>
              <th>Nurse ID</th>
              <th>First Name</th>
              <th>Room Name</th>
              <th>Comment</th>
              <th>Rating</th>
            </tr>
          </thead>
          <tbody>
            {feedback.map((item) => (
              <tr key={item.nurseId}>
                <td>{item.nurseId}</td>
                <td>{item.nurseFirstName || "N/A"}</td>
                {/* <td>{item.nurseDescription || "N/A"}</td>
                <td>{item.nurseCategory || "N/A"}</td> */}
                <td>{item.nurseRoomName || "N/A"}</td>
                <td>{item.comment || "No comment"}</td>
                <td>{item.rating || "Unrated"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p className="no-data-message">No feedback available for nurses.</p>
      )}
    </div>
  );
};

export default NurseFeedback;
