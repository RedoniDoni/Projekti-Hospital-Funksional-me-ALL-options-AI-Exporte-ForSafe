  import api from "../axios";

  export const createPatient = async (patientData) => {
      try {
        const response = await api.post('/patient/create', patientData);
        return response.data;
      } catch (error) {
        console.error('Error creating patient:', error);
        throw error;
      }
    };
    export const fetchAllPatients = async () => {
      try {
        const response = await api.get('/patient/all');
        return response.data.data; // Extract the patients array
      } catch (error) {
        console.error('Error fetching patients:', error);
        throw error;
      }
    };
    
  export const deletePatient = async (id) => {
    try {
        const response = await api.delete(`/patient/${id}`);
        console.log('Deleted Patient:', response);
        return response.data;
    } catch (error) {
        console.error('Error deleting patient:', error);
        throw error;
    }
  };

  /**
  * Update a patient by ID
  */
  export const updatePatient = async (id, patientData) => {
    console.log("Updating patient with data:", patientData); // Debug log
    try {
      const response = await api.put(`/patient/update/${id}`, patientData);
      console.log("Update response:", response.data); // Debug log
      return response.data;
    } catch (error) {
      console.error("Error updating patient:", error);
      throw error;
    }
  };

  export const fetchPatientById = async (id) => {
    const response = await api.get(`/patient/${id}`);
    return response.data;
  };

  export const fetchPatientsInRoom = async () => {
    try {
      const response = await api.get('/patient/rooms');
      return response.data; // Extract the patients array
    } catch (error) {
      console.error('Error fetching patients:', error);
      throw error;
    }
  };
  



    