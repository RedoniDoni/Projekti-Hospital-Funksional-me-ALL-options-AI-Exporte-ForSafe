import React, { useEffect, useState } from "react";
import { getPatientDiagnosis } from "../../../services/requests/diagnosis";
import "./diagnosis.scss";

const ShowDiagnosis = () => {
  const [diagnosis, setDiagnosis] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const token = localStorage.getItem("authToken");
        const decodedToken = JSON.parse(atob(token.split(".")[1]));
        const patientId = decodedToken.userId;

        const data = await getPatientDiagnosis(patientId);
        setDiagnosis(data.data);
      } catch (err) {
        console.error("Error fetching appointments:", err);
        setError("Failed to fetch appointments. Please try again later.");
      } finally {
        setLoading(false);
      }
    };
    fetchAppointments();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="diagnosis-container">
      <h1>Diagnosis Details</h1>
      {diagnosis.length > 0 ? (
        diagnosis.map((item) => (
          <div key={item.id} className="diagnosis-item">
            <p>
              <strong>Diagnosis Details:</strong> {item.diagnosisDetails}
            </p>
            <p>
              <strong>Treatment Plan:</strong> {item.treatmentPlan}
            </p>
          </div>
        ))
      ) : (
        <p className="no-diagnosis">No diagnosis available.</p>
      )}
    </div>
  );
};

export default ShowDiagnosis;
