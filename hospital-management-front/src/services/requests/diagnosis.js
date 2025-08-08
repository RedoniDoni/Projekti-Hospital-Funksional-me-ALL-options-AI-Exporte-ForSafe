import api from '../axios'

export const createDiagnosis = async (diagnosisData) => {
    try {
      const response = await api.post('/diagnosis/create', diagnosisData);
      return response.data;
    } catch (error) {
      console.error('Error creating diagnosis:', error);
      throw error;
    }
  };
  
  export const getPatientDiagnosis = async (patientId) => {
    try {
      const response = await api.get(`/diagnosis/${patientId}`);
      return response.data;
    } catch (error) {
      console.error('Error creating diagnosis:', error);
      throw error;
    }
  };
  
  export const getDiagnosisByDoctorId = async (doctorId) => {
    try {
      const response = await api.get(`/diagnosis/${doctorId}`);
      return response.data;
    } catch (error) {
      console.error('Error creating diagnosis:', error);
      throw error;
    }
  };

  export const deleteDiagnosisById = async (id) => {
    try {
      const response = await api.delete(`/diagnosis/${id}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting diagnosis:', error);
      throw error;
    }
  };
  
  export const updateDiagnosisById = async (id, updatedDiagnosis) => {
    try {
      const response = await api.put(`/diagnosis/update/${id}`, updatedDiagnosis);
      return response.data;
    } catch (error) {
      console.error('Error updating diagnosis:', error);
      throw error;
    }
  };
  