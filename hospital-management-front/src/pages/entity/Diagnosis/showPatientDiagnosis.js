import React, { useEffect, useState } from 'react';
import { getDiagnosisByDoctorId, deleteDiagnosisById, updateDiagnosisById } from '../../../services/requests/diagnosis';

const ShowPatientDiagnosis = () => {
  const [diagnosis, setDiagnosis] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editing, setEditing] = useState(null);
  const [formData, setFormData] = useState({ diagnosisDetails: '', treatmentPlan: '' });

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const token = localStorage.getItem("authToken");
        const decodedToken = JSON.parse(atob(token.split(".")[1]));
        const doctorId = decodedToken.userId;

        const data = await getDiagnosisByDoctorId(doctorId);
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

  const handleEdit = (item) => {
    setEditing(item.id);
    setFormData({ diagnosisDetails: item.diagnosisDetails, treatmentPlan: item.treatmentPlan });
  };

  const handleUpdate = async (id) => {
    try {
      setLoading(true);
      const updatedDiagnosis = await updateDiagnosisById(id, formData);
      setDiagnosis(diagnosis.map(item => item.id === id ? updatedDiagnosis : item));
      setEditing(null);
    } catch (err) {
      console.error("Error updating diagnosis:", err);
      setError("Failed to update diagnosis. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    try {
      setLoading(true);
      const result = await deleteDiagnosisById(id);
      if (result) {
        setDiagnosis(diagnosis.filter(item => item.id !== id));
      }
    } catch (err) {
      console.error("Error deleting diagnosis:", err);
      setError("Failed to delete diagnosis. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div style={{margin: "20px"}}>
      <h1>Patient Diagnosis</h1>
      {diagnosis.map((item) => (
        <div key={item.id} style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc' }}>
          {editing === item.id ? (
            <>
              <input
                type="text"
                value={formData.diagnosisDetails}
                onChange={(e) => setFormData({ ...formData, diagnosisDetails: e.target.value })}
              />
              <input
                type="text"
                value={formData.treatmentPlan}
                onChange={(e) => setFormData({ ...formData, treatmentPlan: e.target.value })}
              />
              <button onClick={() => handleUpdate(item.id)} className='edit-button'>Update</button>
              <button onClick={() => setEditing(null)} className='delete-button'>Cancel</button>
            </>
          ) : (
            <>
              <p><strong>Diagnosis Details:</strong> {item.diagnosisDetails}</p>
              <p><strong>Treatment Plan:</strong> {item.treatmentPlan}</p>
              <button onClick={() => handleEdit(item)} className='edit-button'>Edit</button>
              <button onClick={() => handleDelete(item.id)} className='delete-button' >Delete</button>
            </>
          )}
        </div>
      ))}
    </div>
  );
};

export default ShowPatientDiagnosis;
